package app.unistat.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "head_of_department")
    private Lector headOfDepartment;
    @ManyToMany
    @JoinTable(
            name = "department_lector",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "lector_id")
    )
    private Set<Lector> lectors;

    public Department() {
    }

    public Department(String name, Lector headOfDepartment,
                      Set<Lector> lectors) {
        this.name = name;
        this.headOfDepartment = headOfDepartment;
        this.lectors = lectors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lector getHeadOfDepartment() {
        return headOfDepartment;
    }

    public void setHeadOfDepartment(Lector headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    public Set<Lector> getLectors() {
        return lectors;
    }

    @Override
    public String toString() {
        return "Department{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", headOfDepartment=" + headOfDepartment
                + ", lectors=" + lectors
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
        Department department = (Department) o;
        return Objects.equals(id, department.id)
                && Objects.equals(name, department.name)
                && Objects.equals(headOfDepartment, department.headOfDepartment)
                && Objects.equals(lectors, department.lectors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, headOfDepartment, lectors);
    }
}
