package app.unistat.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lectors")
public class Lector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Degree degree;
    private BigDecimal salary;
    @ManyToMany(mappedBy = "lectors")
    private Set<Department> departments;

    public Lector() {
    }

    public Lector(String firstName, String lastName, Degree degree,
                  BigDecimal salary, Set<Department> departments) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.degree = degree;
        this.salary = salary;
        this.departments = departments;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public enum Degree {
        ASSISTANT("assistant"),
        ASSOCIATE_PROFESSOR("associate professor"),
        PROFESSOR("professor");

        private final String value;

        Degree(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Override
    public String toString() {
        return "Lector{"
                + "id=" + id
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", degree=" + degree
                + ", salary=" + salary
                + ", departments=" + departments
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lector lector = (Lector) o;
        return Objects.equals(id, lector.id)
                && Objects.equals(firstName, lector.firstName)
                && Objects.equals(lastName, lector.lastName)
                && degree == lector.degree
                && Objects.equals(salary, lector.salary)
                && Objects.equals(departments, lector.departments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, degree, salary, departments);
    }
}
