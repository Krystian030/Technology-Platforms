package Jandy.Krystian.character.service;

import Jandy.Krystian.character.entity.Employee;
import Jandy.Krystian.character.entity.comparator.EmployeeBySalaryComparator;
import Jandy.Krystian.character.repository.EmployeeRepository;

import java.util.*;
import lombok.Getter;

@Getter
public class EmployeeService {
    private  final EmployeeRepository employeeRepository;
    private final String option;

    public EmployeeService(EmployeeRepository employeeRepository, String option) {
        this.employeeRepository = employeeRepository;
        this.option = option;
    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.getAll();
    }

    public String getOption(){
        return option;
    }

    public Set<Employee> createSubordinates(){
        if (option.equalsIgnoreCase("no sort")) {
            return new HashSet<Employee>();
        }
        else if (option.equalsIgnoreCase("sort")) {
            return new TreeSet<Employee>();
        }
        else if (option.equalsIgnoreCase("alternative sort")) {
            return new TreeSet<Employee>(new EmployeeBySalaryComparator());
        }
        return new HashSet<Employee>();
    }

    public Map<Employee,Integer> createMapStatistics(){
        if (option.equalsIgnoreCase("no sort")) {
            return new HashMap<Employee,Integer>();
        }
        else if (option.equalsIgnoreCase("sort")) {
            return new TreeMap<Employee,Integer>();
        }
        else if (option.equalsIgnoreCase("alternative sort")) {
            return new TreeMap<Employee,Integer>(new EmployeeBySalaryComparator());
        }
        return new HashMap<Employee,Integer>();
    }

    public void add(Employee e){
        employeeRepository.add(e);
    }

    public void delete(Employee e) {
        employeeRepository.delete(e);
    }

    public int count(Employee e,Map<Employee,Integer> map, int counter){

        if (!e.getSubordinates().isEmpty()) {
            for (Employee m : e.getSubordinates()) {
                counter += count(m,map,0);
            }
        }
        map.put(e,counter++);

        return counter;
    }

    public Map<Employee,Integer> createEmployeeServiceStatistics(){
        Map<Employee,Integer> map = createMapStatistics();
        for(Employee e : employeeRepository.getAll()) {
            count(e,map,0);
        }
        return map;
    }


}

