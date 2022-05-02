package Jandy.Krystian.character.entity.comparator;

import Jandy.Krystian.character.entity.Employee;

import java.util.Comparator;

public class EmployeeBySalaryComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2){

        int ret = Double.compare(e1.getSalary(),e2.getSalary());
        if (ret == 0) {
            ret = e1.getName() == null
                    ? (e2.getName() == null ? 0 : 1)
                    : e1.getName().compareTo(e2.getName());
        }

        return ret;
    }
}
