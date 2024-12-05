package service;

import moduls.Patient;

import java.util.List;
import java.util.Map;

public interface PatientService {
    Patient getPatientById(Long id);
    Map<Long, Patient> getPatientByAge(int age);
    List<Patient> sortPatientsByAge(String ascOrDesc);
}
