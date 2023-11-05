package app.unistat.config;

import app.unistat.model.Department;
import app.unistat.model.Lector;
import app.unistat.service.DepartmentService;
import app.unistat.service.LectorService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class DataInitializer {
    private final DepartmentService departmentService;
    private final LectorService lectorService;

    public DataInitializer(DepartmentService departmentService, LectorService lectorService) {
        this.departmentService = departmentService;
        this.lectorService = lectorService;
    }

    @PostConstruct
    public void initialize() {
        Lector john = new Lector("John", "Kirby",
                "assistant", new BigDecimal("3500"), new HashSet<>());
        Lector dave = new Lector("Dave", "Collins",
                "associate professor", new BigDecimal("4500"), new HashSet<>());
        Lector anna = new Lector("Anna", "Fleming",
                "professor", new BigDecimal("6000"), new HashSet<>());
        lectorService.add(john);
        lectorService.add(dave);
        lectorService.add(anna);

        Department mathDepartment = new Department("Mathematics", john, new HashSet<>());
        Department computerScienceDepartment = new Department("Computer Science",
                dave, new HashSet<>());

        mathDepartment.getLectors().add(john);
        computerScienceDepartment.getLectors().add(dave);
        mathDepartment.getLectors().add(anna);
        computerScienceDepartment.getLectors().add(anna);

        departmentService.add(mathDepartment);
        departmentService.add(computerScienceDepartment);
    }
}
