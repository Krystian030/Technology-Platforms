package jandy.krystian.characters.initialize;

import jandy.krystian.characters.entity.Wizard;
import jandy.krystian.characters.entity.School;
import jandy.krystian.characters.service.WizardService;
import jandy.krystian.characters.service.SchoolService;

public class Initialize {
    private WizardService wizardService;
    private SchoolService schoolService;

    public Initialize(WizardService wizardService, SchoolService schoolService) {
        this.wizardService = wizardService;
        this.schoolService = schoolService;
    }

    public void init(){
        School masterSchool = new School("Masters",99);
        School studentSchool = new School("Students", 10);
        School proSchool = new School("ProMages", 130);
        School mediumSchool = new School("Medium", 60);

        schoolService.addTower(masterSchool);
        schoolService.addTower(studentSchool);
        schoolService.addTower(proSchool);
        schoolService.addTower(mediumSchool);

        Wizard wizard1 = new Wizard("Eage1",10, studentSchool);
        Wizard wizard2 = new Wizard("Bage2",6, studentSchool);
        Wizard wizard3 = new Wizard("Cage3",5, studentSchool);

        Wizard wizard4 = new Wizard("Mage4",30, mediumSchool);
        Wizard wizard5 = new Wizard("Mage5",46, mediumSchool);
        Wizard wizard6 = new Wizard("Mage6",33, mediumSchool);

        Wizard wizard7 = new Wizard("Mage7",80, masterSchool);
        Wizard wizard8 = new Wizard("Mage8",98, masterSchool);
        Wizard wizard9 = new Wizard("Mage9",66, masterSchool);

        Wizard wizard10 = new Wizard("Mage10",999, proSchool);
        Wizard wizard11 = new Wizard("Mage11",218, proSchool);
        Wizard wizard12 = new Wizard("Mage12",333, proSchool);

        wizardService.addMage(wizard1);
        wizardService.addMage(wizard2);
        wizardService.addMage(wizard3);
        wizardService.addMage(wizard4);
        wizardService.addMage(wizard5);
        wizardService.addMage(wizard6);
        wizardService.addMage(wizard7);
        wizardService.addMage(wizard8);
        wizardService.addMage(wizard9);
        wizardService.addMage(wizard10);
        wizardService.addMage(wizard11);
        wizardService.addMage(wizard12);
    }

}
