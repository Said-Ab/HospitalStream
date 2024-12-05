package dataBase.dao.daoImpls;

import dataBase.Database;
import dataBase.dao.DaoGenericService;
import dataBase.dao.DaoServicePatient;
import moduls.Hospital;
import moduls.Patient;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaoPatient implements DaoGenericService<Patient>, DaoServicePatient {

    @Override
    public String add(Long hospitalId, Patient patient) {
        for (Hospital hospital : Database.hospitalList) {
            if (hospital.getId().equals(hospitalId)) {
                hospital.getPatients().add(patient);
                return "Patient added successfully.";
            }
        }
        return "Hospital not found.";
    }

    @Override
    public void removeById(Long id) {
        Database.hospitalList.forEach(h -> h.getPatients()
                .removeIf(p -> p.getId().equals(id)));
    }

    @Override
    public String updateById(Long id, Patient patient) {
        for (Hospital hospital : Database.hospitalList) {
            for (Patient pat : hospital.getPatients()) {
                if (pat.getId().equals(id)) {
                    pat.setFirstName(patient.getFirstName());
                    pat.setLastName(patient.getLastName());
                    pat.setAge(patient.getAge());
                    pat.setGender(patient.getGender());
                    return "Patient updated successfully.";
                }
            }
        }
        return "Patient not found.";
    }

    @Override
    public Patient getPatientById(Long id) {
        return Database.hospitalList.stream()
                .flatMap(h -> h.getPatients().stream())
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Map<Long, Patient> getPatientByAge(int age) {
        return Database.hospitalList.stream()
                .flatMap(h -> h.getPatients().stream())
                .filter(p -> p.getAge() == age)
                .collect(Collectors.toMap(Patient::getId, p -> p));
    }


    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return Database.hospitalList.stream()
                .flatMap(h -> h.getPatients().stream())
                .sorted(ascOrDesc.equalsIgnoreCase("asc") ?
                        Comparator.comparingInt(Patient::getAge) :
                        Comparator.comparingInt(Patient::getAge).reversed())
                .collect(Collectors.toList());
    }
}
