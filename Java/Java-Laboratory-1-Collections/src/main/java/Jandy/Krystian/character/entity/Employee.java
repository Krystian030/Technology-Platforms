package Jandy.Krystian.character.entity;

import java.util.Set;
import java.util.Objects;
import lombok.Builder;

@Builder
public class Employee implements Comparable<Employee> {
    private String name;
    private String jobTitle;
    private String department;
    private int clearanceLevel;
    private Double salary;
    Set<Employee> subordinates;

    public Employee(String name, String jobTitle, String department, int clearanceLevel, Double salary, Set<Employee> subordinates) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.department = department;
        this.clearanceLevel = clearanceLevel;
        this.salary = salary;
        this.subordinates = subordinates;
    }
    public void addSubordinates(Employee e) {
        if(this != e) subordinates.add(e);
        else System.out.printf("Nie mozesz sam byc swoim praktykantem!");
    }

    public void removeSubordinates(Employee e) {
        subordinates.remove(e);
    }
    // compareTo
    @Override
    public int compareTo(Employee o) {
        int ret = name == null
                ? (o.name == null ? 0 : 1)
                : name.compareTo(o.name);
        return ret;
    }

    // HashCode method
    @Override
    public int hashCode() {
        int result = name == null ? 0 : name.hashCode();
        result += jobTitle == null ? 0 : 31*jobTitle.hashCode();
        result += department == null ? 0 : 31*department.hashCode();
        result = 31*result+clearanceLevel;

        result = 67*result+Double.hashCode(salary);
        return result;
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return clearanceLevel == employee.clearanceLevel && Objects.equals(name, employee.name) && Objects.equals(jobTitle, employee.jobTitle) && Objects.equals(department, employee.department) && Objects.equals(salary, employee.salary) && Objects.equals(subordinates, employee.subordinates);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", department='" + department + '\'' +
                ", clearanceLevel=" + clearanceLevel +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public int getClearanceLevel() {
        return clearanceLevel;
    }

    public Double getSalary() {
        return salary;
    }

    public Set<Employee> getSubordinates() {
        return subordinates;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setClearanceLevel(int clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setSubordinates(Set<Employee> subordinates) {
        this.subordinates = subordinates;
    }
}
