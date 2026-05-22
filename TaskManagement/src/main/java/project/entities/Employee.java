package project.entities;

import java.io.Serial;
import java.io.Serializable;

public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final int empId;
    private String name;

    public Employee(int emp_id, String name) {
        this.empId = emp_id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getEmpId() {
        return empId;
    }
    public void setName(String name) {
        this.name = name;
    }
}
