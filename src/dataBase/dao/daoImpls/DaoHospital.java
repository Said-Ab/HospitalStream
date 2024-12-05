package dataBase.dao.daoImpls;

import dataBase.Database;
import dataBase.dao.DaoServiceHospital;
import moduls.Hospital;
import moduls.Patient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DaoHospital implements DaoServiceHospital {
    @Override
    public String addHospital(Hospital hospital) {
        Database.hospitalList.add(hospital);
        return "Hospital added successfully.";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return Database.hospitalList.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return Database.hospitalList;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return Database.hospitalList.stream()
                .filter(h -> h.getId().equals(id))
                .flatMap(h -> h.getPatients().stream())
                .collect(Collectors.toList());
    }

    @Override
    public String deleteHospitalById(Long id) {
        Hospital hospital = findHospitalById(id);
        if (hospital != null) {
            Database.hospitalList.remove(hospital);
            return "Hospital deleted successfully.";
        }
        return "Hospital not found.";
    }
    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return Database.hospitalList.stream()
                .filter(h -> h.getAddress().equals(address))
                .collect(Collectors.toMap(Hospital::getHospitalName, h -> h));
    }
}
