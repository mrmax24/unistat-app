package app.unistat.dao;

import app.unistat.model.Department;

import java.util.Optional;

public interface DepartmentDao {
    Department add(Department department);

    Optional<Department> getByName(String name);

    Object[] getDepartmentStatistics(String departmentName);

    Double getAverageSalaryForDepartment(String departmentName);

    Long getEmployeeCountForDepartment(String departmentName);
}
