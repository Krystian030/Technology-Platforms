package jandy.krystian;

import jandy.krystian.characters.entity.Wizard;
import jandy.krystian.characters.entity.School;
import jandy.krystian.characters.initialize.Initialize;
import jandy.krystian.characters.repository.WizardRepository;
import jandy.krystian.characters.repository.SchoolRepository;
import jandy.krystian.characters.service.WizardService;
import jandy.krystian.characters.service.SchoolService;
import jandy.krystian.characters.view.View;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab3");
        Main application = new Main();
        WizardService wizardService = new WizardService(new WizardRepository(emf));
        SchoolService schoolService = new SchoolService(new SchoolRepository(emf));

        Initialize initialize = new Initialize(wizardService, schoolService);
        View view = new View(wizardService, schoolService);

//        view.displayAll();
//        mageService.delete(mageService.findMage("Mage9"));
//        towerService.delete(towerService.findTower("Students"));
//        view.displayAll();

        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        String op;
        view.displayOption();
        while(run) {
            op = scanner.next();
            if ("quit".equalsIgnoreCase(op)) {
                run = false;
            }
            else if ("addMage".equalsIgnoreCase(op)) {
                application.addMage(wizardService, schoolService, scanner);
            }
            else if ("printAll".equalsIgnoreCase(op)) {
                view.displayAll();
            }
            else if ("initData".equalsIgnoreCase(op)) {
                initialize.init();
            }
            else if ("printMages".equalsIgnoreCase(op)) {
                view.displayMages();
            }
            else if ("printTowers".equalsIgnoreCase(op)) {
                view.displayTowers();
            }
            else if ("deleteMage".equalsIgnoreCase(op)) {
                application.deleteMage(wizardService,scanner);
            }
            else if ("deleteTower".equalsIgnoreCase(op)) {
               application.deleteTower(schoolService,scanner);
            }
            else if ("addTower".equalsIgnoreCase(op)) {
                application.addTower(schoolService,scanner);
            }
            else if ("zapytania".equalsIgnoreCase(op)){
                view.displayQuestions();
            }
            else if ("1".equalsIgnoreCase(op)){
                System.out.println("Podaj level graniczny: ");
                Integer level = Integer.parseInt(scanner.next());
                System.out.println("Magowie z poziomem wi??kszym ni??: " + level + ": ");
                view.displayMagesList(wizardService.getBiggerThanLvl(level));
            }
            else if ("2".equalsIgnoreCase(op)){
                System.out.println("Podaj wysoko???? graniczn??: ");
                Integer height = Integer.parseInt(scanner.next());
                System.out.println("Wie??e ni??sze ni?? " + height + ": ");
                view.displayTowerList(schoolService.getLowerThan(height));
            }
            else if ("3".equalsIgnoreCase(op)){
                System.out.println("Podaj level graniczny: ");
                Integer level = Integer.parseInt(scanner.next());
                System.out.println("Podaj nazw?? wie??y: ");
                String towerName = scanner.next();
                System.out.println("Magowie z levelem wi??kszym ni?? " + level + " z wie??y " + towerName + ": ");
                view.displayMagesList(wizardService.getMageBiggerThanLvlFromTower(level,towerName));
            }
            else if ("4".equalsIgnoreCase(op)){
                System.out.println("Podaj wp??yw graniczny: ");
                Integer influence = Integer.parseInt(scanner.next());
                System.out.println("Szko??y z wp??ywem wi??ksze ni?? " + influence + ": ");
                view.displayTowerList(schoolService.getBiggerThan(influence));
            }
            else if ("5".equalsIgnoreCase(op)){
                System.out.println("Podaj level graniczny: ");
                Integer level = Integer.parseInt(scanner.next());
                System.out.println("Podaj nazw?? szko??y: ");
                String schoolName = scanner.next();
                System.out.println("Magowie z levelem wi??kszym ni?? " + level + " z szko??y " + schoolName + ": ");
                view.displayMagesList(wizardService.getMageBiggerThanLvlFromSchool(level,schoolName));
            }
            else if ("printMageFromTower".equalsIgnoreCase(op)){
                System.out.println("Podaj nazw?? wie??y: ");
                view.printMagesFromTower(scanner.next());
            }
            else {
                view.displayOption();
            }
        }

        emf.close();
    }

    private void deleteTower(SchoolService schoolService, Scanner scanner) {
        System.out.println("Podaj nazw?? wi????y: ");
        String towerName = scanner.next();
        School school = schoolService.findTower(towerName);
        if (school != null) {
            schoolService.delete(school);
            System.out.println("Wie??a zosta??a usuni??ta!\n");
        }
        else {
            System.out.println("Wie??a o nazwie " + towerName + " nie istniej?? w bazie!");
        }
    }

    private void deleteMage(WizardService wizardService, Scanner scanner) {
        System.out.println("Podaj imi?? maga: ");
        String mageName = scanner.next();
        Wizard wizard = wizardService.findMage(mageName);
        if (wizard != null) {
            wizardService.delete(wizard);
            System.out.println("Mag zosta?? usuni??ty!\n");
        }
        else {
            System.out.println("Mag o imieniu " + mageName + " nie istniej?? w bazie!");
        }
    }

    private void addTower(SchoolService schoolService, Scanner scanner) {
        School school = new School();
        System.out.println("Podaj nazw??: ");
        school.setName(scanner.next());
        System.out.println("Podaj wysoko????: ");
        school.setInfluence(Integer.parseInt(scanner.next()));
        school.setWizards(new ArrayList<>());
        schoolService.addTower(school);
        System.out.println("Wie??a zosta??a dodana do bazy!");
    }

    public void addMage(WizardService wizardService, SchoolService schoolService, Scanner scanner){
        Wizard wizard = new Wizard();
        System.out.println("Podaj imi??: ");
        wizard.setName(scanner.next());

        Wizard tmp = wizardService.findMage(wizard.getName());
        if(tmp==null) {
            System.out.println("Podaj lvl: ");
            wizard.setLevel(Integer.parseInt(scanner.next()));
            System.out.println("Podaj nazw?? wie??y: ");
            School school = null;
            String name = scanner.next();
            school = schoolService.findTower(name);
            if (school != null) {
                wizard.setSchool(school);
                wizardService.addMage(wizard);
                System.out.println("Mag " + wizard.getName() + " zosta?? dodany do bazy!\n");
            } else {
                System.out.println("Wie??a o nazwie " + name + " nie istnieje! Spr??buj ponownie p????niej i dodaj wie???? o takiej nazwie!");
            }
        }
        else {
            System.out.println("Mag o takim imieniu istnieje ju?? w bazie danych!");
        }
    }
}

