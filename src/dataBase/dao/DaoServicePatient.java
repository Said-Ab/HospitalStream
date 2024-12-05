package dataBase.dao;

import moduls.Patient;

import java.util.List;
import java.util.Map;

public interface DaoServicePatient {
    Patient getPatientById(Long id);
    Map<Long, Patient> getPatientByAge(int age);
    List<Patient> sortPatientsByAge(String ascOrDesc);
}
