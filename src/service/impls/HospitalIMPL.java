package service.impls;

import dataBase.dao.DaoServiceHospital;
import dataBase.dao.daoImpls.DaoHospital;
import moduls.Hospital;
import moduls.Patient;
import service.HospitalService;

import java.util.List;
import java.util.Map;

public class HospitalIMPL implements HospitalService {
    DaoServiceHospital hospitalDao=new DaoHospital();
    @Override
    public String addHospital(Hospital hospital) {

        return hospitalDao.addHospital(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalDao.findHospitalById(id);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDao.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return hospitalDao.getAllPatientFromHospital(id);
    }

    @Override
    public String deleteHospitalById(Long id) {
        return hospitalDao.deleteHospitalById(id);
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return hospitalDao.getAllHospitalByAddress(address);
    }
}