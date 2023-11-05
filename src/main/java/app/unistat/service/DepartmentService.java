package app.unistat.service;

import app.unistat.model.Department;
import java.util.Optional;

public interface DepartmentService {
    Department add(Department department);

    Optional<Department> getByName(String name);

    Object[] getDepartmentStatistics(String departmentName);

    Double getAverageSalaryForDepartment(String departmentName);

    Long getEmployeeCountForDepartment(String departmentName);
}
