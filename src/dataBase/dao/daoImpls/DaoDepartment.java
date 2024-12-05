package dataBase.dao.daoImpls;

import dataBase.Database;
import dataBase.dao.DaoGenericService;
import dataBase.dao.DaoServiceDepartment;
import moduls.Department;
import moduls.Hospital;

import java.util.List;
import java.util.stream.Collectors;

public class DaoDepartment implements DaoServiceDepartment, DaoGenericService<Department> {
    @Override
    public String add(Long hospitalId, Department department) {
        for (Hospital hospital : Database.hospitalList) {
            if (hospital.getId() == hospitalId) {
                hospital.getDepartments().add(department);
                return "Success";
            }
        }
        return "Failure";
    }

    @Override
    public void removeById(Long id) {
        Database.hospitalList.forEach(h -> h.getDepartments()
                .removeIf(d -> d.getId().equals(id)));
    }

    @Override
    public String updateById(Long id, Department department) {
        for (Hospital hospital : Database.hospitalList) {
            for (Department dep : hospital.getDepartments()) {
                if (dep.getId().equals(id)) {
                    dep.setDepartmentName(department.getDepartmentName());
                    return "Department updated successfully.";
                }
            }
        }
        return "Department not found.";
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return Database.hospitalList.stream()
                .filter(h -> h.getId().equals(id))
                .flatMap(h -> h.getDepartments().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Department findDepartmentByName(String name) {
        return Database.hospitalList.stream()
                .flatMap(h -> h.getDepartments().stream())
                .filter(d -> d.getDepartmentName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
