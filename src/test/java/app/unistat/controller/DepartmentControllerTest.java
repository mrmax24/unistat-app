package app.unistat.controller;

import app.unistat.model.Department;
import app.unistat.model.Lector;
import app.unistat.service.DepartmentService;
import app.unistat.service.LectorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class DepartmentControllerTest {
    private DepartmentController departmentController;

    @Mock
    private DepartmentService departmentService;

    @Mock
    private LectorService lectorService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        departmentController = new DepartmentController(departmentService, lectorService);
    }

    @Test
    public void testGetHeadOfDepartmentInfo() {
        Department department = new Department();
        department.setName("Test Department");

        Lector headOfDepartment = new Lector();
        headOfDepartment.setFirstName("John");
        headOfDepartment.setLastName("Kirby");

        department.setHeadOfDepartment(headOfDepartment);

        when(departmentService.getByName("Test Department")).thenReturn(Optional.of(department));

        String[] tokens = {"Who", "is", "head", "of", "department", "Test", "Department"};
        departmentController.getHeadOfDepartmentInfo(tokens);
    }

    @Test
    public void testShowDepartmentStatistics() {
        Department department = new Department();
        department.setName("Test Department");

        when(departmentService.getByName("Test Department")).thenReturn(Optional.of(department));

        Object[] departmentStatistics = {10L, 5L, 3L};

        when(departmentService.getDepartmentStatistics("Test Department")).thenReturn(departmentStatistics);

        String[] tokens = {"Show", "Test", "Department", "statistics"};
        departmentController.showDepartmentStatistics(tokens);
    }

    @Test
    public void testShowAverageSalaryForDepartment() {
        Department department = new Department();
        department.setName("Test Department");

        when(departmentService.getByName("Test Department")).thenReturn(Optional.of(department));

        when(departmentService.getAverageSalaryForDepartment("Test Department")).thenReturn(50000.0);

        String[] tokens = {"Show", "the", "average", "salary", "for", "the", "department", "Test", "Department"};
        departmentController.showAverageSalaryForDepartment(tokens);
    }

    @Test
    public void testShowEmployeeCountForDepartment() {
        Department department = new Department();
        department.setName("Test Department");

        when(departmentService.getByName("Test Department")).thenReturn(Optional.of(department));

        when(departmentService.getEmployeeCountForDepartment("Test Department")).thenReturn(20L);

        String[] tokens = {"Show", "count", "of", "employee", "for", "department", "Test", "Department"};
        departmentController.showEmployeeCountForDepartment(tokens);
    }

    @Test
    public void testGlobalSearch() {
        List<String> searchResults = new ArrayList<>();
        searchResults.add("John Kirby");
        searchResults.add("Anna Fleming");

        when(lectorService.globalSearch("John")).thenReturn(searchResults);

        String[] tokens = {"Global", "search", "by", "John"};
        departmentController.globalSearch(tokens);
    }
}
