package Jandy.Krystian.character.initialize;

import Jandy.Krystian.character.entity.Employee;
import Jandy.Krystian.character.service.EmployeeService;
import Jandy.Krystian.character.service.MageService;

import java.util.Set;

public class TestInitialize {

//    private final MageService mageService;
    private final EmployeeService employeeService;

    public TestInitialize(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void init(){

        Employee e1 = Employee.builder().name("Adams").jobTitle("Informatyk").subordinates(employeeService.createSubordinates()).clearanceLevel(3).salary(5000.25).department("Tech").build();
        Employee e2 = Employee.builder().name("Luiz").jobTitle("Szef").subordinates(employeeService.createSubordinates()).clearanceLevel(99).salary(1000000.99).department("Tech").build();
        Employee e3 = Employee.builder().name("Patric").jobTitle("Agent").subordinates(employeeService.createSubordinates()).clearanceLevel(13).salary(15000.25).department("Ubez").build();
        Employee e4 = Employee.builder().name("Waldemard").jobTitle("Prezes").subordinates(employeeService.createSubordinates()).clearanceLevel(43).salary(16000.45).department("Polityk").build();
        Employee e5 = Employee.builder().name("Cris").jobTitle("Programista").subordinates(employeeService.createSubordinates()).clearanceLevel(63).salary(50000.15).department("Python").build();
        Employee e6 = Employee.builder().name("Max").jobTitle("Programista").subordinates(employeeService.createSubordinates()).clearanceLevel(73).salary(35000.85).department("C++").build();
        Employee e7 = Employee.builder().name("Sajmon").jobTitle("Agent").subordinates(employeeService.createSubordinates()).clearanceLevel(83).salary(25000.95).department("JAVA").build();
        Employee e8 = Employee.builder().name("Garry").jobTitle("Prawnik").subordinates(employeeService.createSubordinates()).clearanceLevel(93).salary(15000.15).department("JAVA").build();
        Employee e9 = Employee.builder().name("Robert").jobTitle("Agent").subordinates(employeeService.createSubordinates()).clearanceLevel(23).salary(45000.05).department("C++").build();
        Employee e10 = Employee.builder().name("Brayan").jobTitle("Księgowy").subordinates(employeeService.createSubordinates()).clearanceLevel(12).salary(15000.35).department("Python").build();

        employeeService.add(e1);
            e1.addSubordinates(e9);

        employeeService.add(e2);
        e2.addSubordinates(e3);
        e2.addSubordinates(e4);
            e4.addSubordinates(e6);
            e4.addSubordinates(e7);
            e4.addSubordinates(e8);
        e2.addSubordinates(e5);

        employeeService.add(e10);




/*
        Mage mageArnold = Mage.builder().level(99).name("Arnold").power(999.99).apprentices(mageService.createApprentices()).build();

        Mage mageHarry = Mage.builder().level(18).name("Harry").power(123.12).apprentices(mageService.createApprentices()).build();

        Mage mageBrayan = Mage.builder().level(31).name("Brayan").power(332.16).apprentices(mageService.createApprentices()).build();

        Mage mageAdam = Mage.builder().level(10).name("Adam").power(50.99).apprentices(mageService.createApprentices()).build();

        Mage mageGary = Mage.builder().level(999).name("Gary").power(9999.999).apprentices(mageService.createApprentices()).build();

        Mage mageHaldor = Mage.builder().level(200).name("Halord").power(299.81).apprentices(mageService.createApprentices()).build();

        Mage mageAgatha = Mage.builder().level(800).name("Agatha").power(9980.11).apprentices(mageService.createApprentices()).build();

        Mage mageChristina = Mage.builder().level(18).name("Christina").power(113.12).apprentices(mageService.createApprentices()).build();

        Mage mageLarry = Mage.builder().level(21).name("Larry").power(21.12).apprentices(mageService.createApprentices()).build();

        Mage mageHoracy = Mage.builder().level(188).name("Horacy").power(188.18).apprentices(mageService.createApprentices()).build();

        mageService.add(mageGary);
        mageService.add(mageHaldor);
        mageService.add(mageHoracy);

        mageGary.addApprentices(mageAgatha);
        mageGary.addApprentices(mageAdam);
        mageGary.addApprentices(mageArnold);

        mageAgatha.addApprentices(mageHarry);
        mageAgatha.addApprentices(mageBrayan);
        mageAgatha.addApprentices(mageChristina);

        mageHarry.addApprentices(mageBrayan);
        mageHarry.addApprentices(mageChristina);

        mageHaldor.addApprentices(mageLarry);

 */
    }
}
