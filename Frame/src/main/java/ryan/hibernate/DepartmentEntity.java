package ryan.hibernate;

import java.util.HashSet;
import java.util.Set;

public class DepartmentEntity {
    private int id;
    private String name;
    private Set<EmployeeEntity> employeeEntities = new HashSet<EmployeeEntity>();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeEntity> getEmployeeEntities() {
        return employeeEntities;
    }

    public void setEmployeeEntities(Set<EmployeeEntity> employeeEntities) {
        this.employeeEntities = employeeEntities;
    }
}
