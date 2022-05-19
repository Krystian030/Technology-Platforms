package Jandy.Krystian.character.view;

import Jandy.Krystian.character.entity.Employee;
import Jandy.Krystian.character.service.EmployeeService;
import Jandy.Krystian.view.View;

import java.util.Map;

public class EmployeeView implements View {
    private final EmployeeService employeeService;


    public EmployeeView(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void display() {

        System.out.println("==========================================\nWidok: ");
        for (Employee e : employeeService.getAllEmployee()) {
            displayApprentices(e,"-");
        }
        System.out.println("==========================================\n");

        System.out.println("==========================================\nStatystyki: ");
        for(Map.Entry<Employee,Integer> e : employeeService.createEmployeeServiceStatistics().entrySet()){
            System.out.println(e.getKey() + " : " + e.getValue());
        }
        System.out.println("==========================================\n");

    }

    public void displayApprentices(Employee e,String indentation) {

        System.out.println(indentation +  e);
        if (!e.getSubordinates().isEmpty()) {
            for (Employee es : e.getSubordinates()) {
                displayApprentices(es,indentation+"-");
            }
        }
    }
}

