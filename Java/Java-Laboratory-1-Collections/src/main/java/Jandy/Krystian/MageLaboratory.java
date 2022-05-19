package Jandy.Krystian;
import Jandy.Krystian.character.entity.comparator.EmployeeBySalaryComparator;
import Jandy.Krystian.character.entity.comparator.MageByLevelComparator;
import Jandy.Krystian.character.initialize.TestInitialize;
import Jandy.Krystian.character.repository.EmployeeRepository;
import Jandy.Krystian.character.repository.MageRepository;
import Jandy.Krystian.character.service.EmployeeService;
import Jandy.Krystian.character.service.MageService;
import Jandy.Krystian.character.view.EmployeeView;
import Jandy.Krystian.character.view.MageView;
import Jandy.Krystian.view.View;

public class MageLaboratory {

    public static void main(String[] args) {
        String argument = (args.length == 1) ? args[0] : null;
        MageLaboratory application = new MageLaboratory();

        EmployeeService employeeService = application.createEmployeeService(argument);
        TestInitialize testInitialize = new TestInitialize(employeeService);
        testInitialize.init();

//        View mageView = new MageView(employeeService);
//        mageView.display();
        View employeeView = new EmployeeView(employeeService);
        employeeView.display();

    }

    public EmployeeService createEmployeeService(String argument){
        if (argument.equalsIgnoreCase("no sort")) {
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


    public MageService createMageService(String argument){
        if (argument.equalsIgnoreCase("no sort")) {
            return new MageService(new MageRepository(false),argument);
        }
        else if (argument.equalsIgnoreCase("sort")) {
            return new MageService(new MageRepository(true),argument);
        }
        else if (argument.equalsIgnoreCase("alternative sort")) {
            return new MageService(new MageRepository(new MageByLevelComparator()),argument);
        }
        else {
            return new MageService(new MageRepository(),argument);
        }
    }
}


