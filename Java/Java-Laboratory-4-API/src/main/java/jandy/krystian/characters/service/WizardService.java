package jandy.krystian.characters.service;

import jandy.krystian.characters.entity.Wizard;
import jandy.krystian.characters.repository.WizardRepository;

import java.util.ArrayList;
import java.util.List;

public class WizardService {
    private WizardRepository repository;

    public WizardService(WizardRepository repository) {
        this.repository = repository;
    }

    public List<Wizard> getAllMages() {
        return repository.getAll();
    }

    public void addMage(Wizard wizard){
        repository.add(wizard);
    }

    public void delete(Wizard wizard) {
        repository.delete(wizard);
    }

    public Wizard findMage(String name) {
        return repository.find(name);
    }

    public void updateMage(Wizard wizard){
        repository.update(wizard);
    }

    public void refresh(Wizard wizard){
        repository.refresh(wizard);
    }

    public ArrayList<Wizard> getBiggerThanLvl(int level) {
       return repository.getBiggerThanLvl(level);
    }
    public ArrayList<Wizard> getMageBiggerThanLvlFromTower(int level, String towerName){
        return repository.getMageBiggerThanLvlFromTower(level,towerName);
    }
    public ArrayList<Wizard> getMageBiggerThanLvlFromSchool(int level, String towerName){
        return repository.getMageBiggerThanLvlFromSchool(level,towerName);
    }
}
