package app.unistat.controller;

import app.unistat.model.Department;
import app.unistat.service.DepartmentService;
import app.unistat.service.LectorService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Controller;

@Controller
public class DepartmentController {
    private final DepartmentService departmentService;
    private final LectorService lectorService;
    private final Scanner scanner = new Scanner(System.in);

    public DepartmentController(DepartmentService departmentService,
                                LectorService lectorService) {
        this.departmentService = departmentService;
        this.lectorService = lectorService;
    }

    public void runConsoleInterface() {
        while (true) {
            System.out.println("Enter a command: ");
            String command = scanner.nextLine();
            String[] tokens = command.split(" ");

            if (command.isEmpty()) {
                System.out.println("Invalid command format");
                continue;
            }

            if (tokens[0].equals("Who")) {
                getHeadOfDepartmentInfo(tokens);

            } else if (tokens[tokens.length - 1].equals("statistics")) {
                showDepartmentStatistics(tokens);

            } else if (tokens[2].equals("average")) {
                showAverageSalaryForDepartment(tokens);

            } else if (tokens[1].equals("count")) {
                showEmployeeCountForDepartment(tokens);

            } else if (tokens[0].equals("Global")) {
                globalSearch(tokens);

            } else {
                System.out.println("Invalid command format");
            }
        }
    }

    public void getHeadOfDepartmentInfo(String[] tokens) {
        if (tokens[1].equals("is") && tokens[2].equals("head")
                && tokens[3].equals("of") && tokens[4].equals("department")) {

            StringBuilder departmentNameBuilder = new StringBuilder();
            for (int i = 5; i < tokens.length; i++) {
                departmentNameBuilder.append(tokens[i]).append(" ");
            }
            String departmentName = departmentNameBuilder.toString().trim();

            Optional<Department> departmentOptional = departmentService.getByName(departmentName);

            if (departmentOptional.isPresent()) {
                Department department = departmentOptional.get();
                if (department.getHeadOfDepartment() != null) {
                    String firstName = department.getHeadOfDepartment().getFirstName();
                    String lastName = department.getHeadOfDepartment().getLastName();
                    System.out.println("Head of " + departmentName + " department is "
                            + firstName + " " + lastName);
                } else {
                    System.out.println("Head of " + "'" + departmentName + "'" + " is not specified");
                }
            } else {
                System.out.println("Department " + "'" + departmentName + "'" + " is not found");
            }
        } else {
            System.out.println("Invalid command format");
        }
    }

    public void showDepartmentStatistics(String[] tokens) {
        if (tokens != null && tokens[0].equals("Show")
                && tokens[tokens.length - 1].equals("statistics")) {

            StringBuilder departmentNameBuilder = new StringBuilder();
            for (int i = 1; i < tokens.length - 1; i++) {
                departmentNameBuilder.append(tokens[i]).append(" ");
            }
            String departmentName = departmentNameBuilder.toString().trim();

            Optional<Department> departmentOptional =
                        departmentService.getByName(departmentName);

            if (departmentOptional.isPresent()) {
                Object[] departmentStatistics =
                        departmentService.getDepartmentStatistics(departmentName);

                if (departmentStatistics != null && departmentStatistics.length >= 3) {
                    Long assistantsCount = (Long) departmentStatistics[0];
                    Long associateProfessorsCount = (Long) departmentStatistics[1];
                    Long professorsCount = (Long) departmentStatistics[2];

                    System.out.println("assistants - " + assistantsCount);
                    System.out.println("associate professors - " + associateProfessorsCount);
                    System.out.println("professors - " + professorsCount);
                } else {
                    System.out.println("Invalid department statistics");
                }
            } else {
                System.out.println("Department " + "'" + departmentName + "'" + " is not found");
            }
        } else {
            System.out.println("Invalid command format");
        }
    }

    public void showAverageSalaryForDepartment(String[] tokens) {
        if (tokens[0].equals("Show") && tokens[1].equals("the")
                && tokens[2].equals("average") && tokens[3].equals("salary")
                && tokens[4].equals("for") && tokens[5].equals("the")
                && tokens[6].equals("department")) {

            StringBuilder departmentNameBuilder = new StringBuilder();
            for (int i = 7; i < tokens.length; i++) {
                departmentNameBuilder.append(tokens[i]).append(" ");
            }
            String departmentName = departmentNameBuilder.toString().trim();

            Optional<Department> departmentOptional = departmentService.getByName(departmentName);

            if (departmentOptional.isPresent()) {
                BigDecimal averageSalaryForDepartment = BigDecimal.valueOf(departmentService
                        .getAverageSalaryForDepartment(departmentName));
                System.out.println("The average salary of " + departmentName
                        + " is " + averageSalaryForDepartment);
            } else {
                System.out.println("Department " + "'" + departmentName + "'" + " is not found");
            }
        } else {
            System.out.println("Invalid command format");
        }
    }

    public void showEmployeeCountForDepartment(String[] tokens) {
        if (tokens[0].equals("Show") && tokens[1].equals("count") && tokens[2].equals("of")
                && tokens[3].equals("employee") && tokens[4].equals("for")
                && tokens[5].equals("department")) {

            StringBuilder departmentNameBuilder = new StringBuilder();
            for (int i = 6; i < tokens.length; i++) {
                departmentNameBuilder.append(tokens[i]).append(" ");
            }
            String departmentName = departmentNameBuilder.toString().trim();

            Optional<Department> departmentOptional = departmentService.getByName(departmentName);

            if (departmentOptional.isPresent()) {
                Long employeeCount = departmentService.getEmployeeCountForDepartment(departmentName);
                System.out.println("The employee count for "
                        + departmentName + " is " + employeeCount);
            } else {
                System.out.println("Department " + "'" + departmentName + "'" + " is not found");
            }
        } else {
            System.out.println("Invalid command format");
        }
    }

    public void globalSearch(String[] tokens) {
        if (tokens[0].equals("Global") && tokens[1].equals("search") && tokens[2].equals("by")) {
            String template = tokens[3];
            List<String> searchResults = lectorService.globalSearch(template);

            if (!searchResults.isEmpty()) {
                System.out.println("Search results:");
                for (String result : searchResults) {
                    System.out.println(result);
                }
            } else {
                System.out.println("No matching results found");
            }
        } else {
            System.out.println("Invalid command format");
        }
    }
}
