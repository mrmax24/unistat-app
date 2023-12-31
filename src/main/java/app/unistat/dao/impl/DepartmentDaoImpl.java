package app.unistat.dao.impl;

import app.unistat.dao.AbstractDao;
import app.unistat.dao.DepartmentDao;
import app.unistat.exception.DataProcessingException;
import app.unistat.model.Department;
import app.unistat.model.Lector;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImpl extends AbstractDao<Department> implements DepartmentDao {
    public DepartmentDaoImpl(SessionFactory factory) {
        super(factory, Department.class);
    }

    @Override
    public Optional<Department> getByName(String departmentName) {
        try (Session session = factory.openSession()) {
            Query<Department> query = session.createQuery(
                    "FROM Department d WHERE d.name = :departmentName", Department.class);
            query.setParameter("departmentName", departmentName);
            return query.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Role with name "
                    + departmentName + " not found", e);
        }
    }

    @Override
    public Object[] getDepartmentStatistics(String departmentName) {
        try (Session session = factory.openSession()) {
            Query<Object[]> query = session.createQuery(
                    "SELECT "
                            + "SUM(CASE WHEN l.degree = :assistant THEN 1 ELSE 0 END)"
                            + " as assistantsCount, "
                            + "SUM(CASE WHEN l.degree = :associateProfessor THEN 1 ELSE 0 END)"
                            + " as associateProfessorsCount, "
                            + "SUM(CASE WHEN l.degree = :professor THEN 1 ELSE 0 END) "
                            + "as professorsCount "
                            + "FROM Department d "
                            + "JOIN d.lectors l "
                            + "WHERE d.name = :departmentName", Object[].class);
            query.setParameter("departmentName", departmentName);
            query.setParameter("assistant", Lector.Degree.ASSISTANT);
            query.setParameter("associateProfessor", Lector.Degree.ASSOCIATE_PROFESSOR);
            query.setParameter("professor", Lector.Degree.PROFESSOR);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get statistics for department: "
                    + departmentName, e);
        }
    }

    @Override
    public Double getAverageSalaryForDepartment(String departmentName) {
        try (Session session = factory.openSession()) {
            Query<Double> query = session.createQuery("SELECT AVG(l.salary) FROM Department d "
                    + "JOIN d.lectors l "
                    + "WHERE d.name = :departmentName", Double.class);
            query.setParameter("departmentName", departmentName);
            Double averageSalary = query.uniqueResult();
            return averageSalary != null ? averageSalary : 0.0;
        }
    }

    @Override
    public Long getEmployeeCountForDepartment(String departmentName) {
        try (Session session = factory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(l) FROM Department d "
                    + "JOIN d.lectors l "
                    + "WHERE d.name = :departmentName", Long.class);
            query.setParameter("departmentName", departmentName);
            Long employeeCount = query.uniqueResult();
            return employeeCount != null ? employeeCount : 0L;
        }
    }
}
