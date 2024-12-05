package dataBase.dao;

import moduls.Department;

import java.util.List;

public interface DaoServiceDepartment {
    List<Department> getAllDepartmentByHospital(Long id);

    Department findDepartmentByName(String name);
}
