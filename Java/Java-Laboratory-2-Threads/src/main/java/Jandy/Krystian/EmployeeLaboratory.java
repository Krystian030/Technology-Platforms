package Jandy.Krystian;

import Jandy.Krystian.character.entity.Employee;
import Jandy.Krystian.character.entity.comparator.EmployeeBySalaryComparator;
import Jandy.Krystian.character.initialize.TestInitialize;
import Jandy.Krystian.character.repository.EmployeeRepository;
import Jandy.Krystian.character.service.EmployeeService;
import Jandy.Krystian.character.view.EmployeeView;
import Jandy.Krystian.view.View;

public class EmployeeLaboratory {

    public static void main(String[] args) {
        String argument = (args.length == 1) ? args[0] : null;
        EmployeeLaboratory application = new EmployeeLaboratory();

        EmployeeService employeeService = application.createEmployeeService(argument);
        TestInitialize testInitialize = new TestInitialize(employeeService);
        testInitialize.init();

        View employeeView = new EmployeeView(employeeService);
        employeeView.display();
    }

    public EmployeeService createEmployeeService(String argument){
        if (argument == null) {
            return new EmployeeService(new EmployeeRepository(),argument);
        }
        else if (argument.equalsIgnoreCase("no sort")) {
            return new EmployeeService(new EmployeeRepository(false),argument);
        }
        else if (argument.equalsIgnoreCase("sort")) {
            return new EmployeeService(new EmployeeRepository(true),argument);
        }
        else if (argument.equalsIgnoreCase("alternative sort")) {
            return new EmployeeService(new EmployeeRepository(new EmployeeBySalaryComparator()),argument);
        }
        else {
            return new EmployeeService(new EmployeeRepository(),argument);
        }
    }

}
