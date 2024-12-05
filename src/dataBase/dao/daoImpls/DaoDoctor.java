package dataBase.dao.daoImpls;

import dataBase.Database;
import dataBase.dao.DaoGenericService;
import dataBase.dao.DaoServiceDoctor;
import moduls.Department;
import moduls.Doctor;
import moduls.Hospital;

import java.util.List;
import java.util.stream.Collectors;

public class DaoDoctor implements DaoServiceDoctor, DaoGenericService<Doctor> {
    @Override
    public String add(Long hospitalId, Doctor doctor) {
        for (Hospital hospital: Database.hospitalList){
            if (hospital.getId()==hospitalId){
                hospital.getDoctors().add(doctor);
                return "Success";
            }
        }
        return "Failure";
    }
    @Override
    public void removeById(Long id) {
        Database.hospitalList.forEach(h -> h.getDoctors()
                .removeIf(d -> d.getId().equals(id)));

    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        for (Hospital hospital : Database.hospitalList) {
            for (Doctor doc : hospital.getDoctors()) {
                if (doc.getId().equals(id)) {
                    doc.setFirstNAme(doctor.getFirstNAme());
                    doc.setLastName(doctor.getLastName());
                    doc.setGender(doctor.getGender());
                    doc.setExperienceYear(doctor.getExperienceYear());
                    return "Doctor updated successfully.";
                }
            }
        }
        return "Doctor not found.";
    }

    @Override
    public Doctor findDoctorById(Long id) {
        return Database.hospitalList.stream()
                .flatMap(h -> h.getDoctors().stream())
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        for (Hospital hospital : Database.hospitalList) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(departmentId)) {
                    List<Doctor> doctors = hospital.getDoctors().stream()
                            .filter(d -> doctorsId.contains(d.getId()))
                            .collect(Collectors.toList());
                    department.getDoctorList().addAll(doctors);
                    return "Doctors assigned successfully.";
                }
            }
        }
        return "Department not found.";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return Database.hospitalList.stream()
                .filter(h -> h.getId().equals(id))
                .flatMap(h -> h.getDoctors().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return Database.hospitalList.stream()
                .flatMap(h -> h.getDepartments().stream())
                .filter(d -> d.getId().equals(id))
                .flatMap(d -> d.getDoctorList().stream())
                .collect(Collectors.toList());
    }
}
