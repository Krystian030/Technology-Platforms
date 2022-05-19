package jandy.krystian.characters.view;

import jandy.krystian.characters.entity.Wizard;
import jandy.krystian.characters.entity.School;
import jandy.krystian.characters.service.WizardService;
import jandy.krystian.characters.service.SchoolService;

import java.util.ArrayList;

public class View {
    private WizardService wizardService;
    private SchoolService schoolService;

    public View(WizardService wizardService, SchoolService schoolService) {
        this.wizardService = wizardService;
        this.schoolService = schoolService;
    }

    public void displayAll(){
        ArrayList<Wizard> wizards = (ArrayList<Wizard>) wizardService.getAllMages();
        ArrayList<School> schools = (ArrayList<School>) schoolService.getAllTower();

        System.out.println("============ Mages ============");
        for (Wizard m : wizards) {
            System.out.println(m);
        }
        System.out.println();
        System.out.println("============ Towers ============");
        for (School t : schools) {
            System.out.println(t);
        }
        System.out.println("\n");
    }

    public void displayMages(){
        ArrayList<Wizard> wizards = (ArrayList<Wizard>) wizardService.getAllMages();
        if (wizards !=null) {
            System.out.println("============ Mages ============");
            for (Wizard m : wizards) {
                System.out.println(m);
            }
            System.out.println();
        }
        else {
            System.out.println("Brak magów w bazie!");
        }
    }

    public void displayMagesList(ArrayList<Wizard> wizards) {
        for (Wizard m : wizards) {
            System.out.println(m.getName());
        }
    }

    public void displayTowerList(ArrayList<School> schools) {
        for (School t : schools) {
            System.out.println(t);
        }
    }

    public void displayTowers(){
        ArrayList<School> schools = (ArrayList<School>) schoolService.getAllTower();
        if(schools !=null) {
            System.out.println("============ Towers ============");
            for (School t : schools) {
                System.out.println(t);
            }
            System.out.println();
        }
        else {
            System.out.println("Brak wieży w bazie!");
        }
    }

    public void displayOption(){
        System.out.println("======================== Option ========================");
        System.out.println("quit");
        System.out.println("addMage");
        System.out.println("addTower");
        System.out.println("printAll");
        System.out.println("printMages");
        System.out.println("printTowers");
        System.out.println("deleteMage");
        System.out.println("deleteTower");
        System.out.println("initData");
        System.out.println("zapytania");
        System.out.println("printMageFromTower");
        System.out.println("=======================================================");
    }

    public void printMagesFromTower(String towerName) {
        School school = schoolService.findTower(towerName);
        if(school != null) {
            System.out.println("Magowie z wieży " + towerName + ": ");
            for (Wizard m : school.getWizards()) {
                System.out.println(m);
            }
        }
        else {
            System.out.println("Brak wieży o zadanej nazwie w bazie!");
        }
    }

    public void displayQuestions(){
        System.out.println("======================== Questions ========================");
        System.out.println("1 -> pobranie wszystkich magów z poziomem większym niż");
        System.out.println("2 -> pobranie wszystkie wież niższych niż");
        System.out.println("3 -> pobranie wszystkich magów z poziomem wyższym niż z danej wieży");
        System.out.println("4 -> pobranie wszystkich szkół z wpływem większym niż");
        System.out.println("5 -> pobranie wszystkich wizardów ze szkoł z poziomem większym niż");
    }
}
