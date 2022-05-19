package Jandy.Krystian.character.repository;

import Jandy.Krystian.character.entity.Employee;
import Jandy.Krystian.character.entity.Mage;
import Jandy.Krystian.repository.MemoryRepository;

import java.util.Comparator;

public class EmployeeRepository extends MemoryRepository<Employee> {
    public EmployeeRepository(){
    }
    public EmployeeRepository(boolean sort){
        super(sort);
    }
    public EmployeeRepository(Comparator comparator) {
        super(comparator);
    }
}

