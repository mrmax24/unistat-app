package app.unistat.service.impl;

import app.unistat.dao.DepartmentDao;
import app.unistat.model.Department;
import app.unistat.service.DepartmentService;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public Department add(Department department) {
        return departmentDao.add(department);
    }

    @Override
    public Optional<Department> getByName(String name) {
        return departmentDao.getByName(name);
    }

    @Override
    public Object[] getDepartmentStatistics(String departmentName) {
        return departmentDao.getDepartmentStatistics(departmentName);
    }

    @Override
    public Double getAverageSalaryForDepartment(String departmentName) {
        return departmentDao.getAverageSalaryForDepartment(departmentName);
    }

    @Override
    public Long getEmployeeCountForDepartment(String departmentName) {
        return departmentDao.getEmployeeCountForDepartment(departmentName);
    }
}
