package app.unistat;

import app.unistat.controller.DepartmentController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UniStatApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context
                = SpringApplication.run(UniStatApplication.class, args);
        DepartmentController departmentController = context.getBean(DepartmentController.class);
        departmentController.runConsoleInterface();
    }
}
