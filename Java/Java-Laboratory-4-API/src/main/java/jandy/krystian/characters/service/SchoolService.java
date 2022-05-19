package jandy.krystian.characters.service;

import jandy.krystian.characters.entity.School;
import jandy.krystian.characters.repository.SchoolRepository;

import java.util.ArrayList;
import java.util.List;

public class SchoolService {
    private SchoolRepository repository;

    public SchoolService(SchoolRepository repository) {
        this.repository = repository;
    }

    public List<School> getAllTower(){
        return repository.getAll();
    }

    public void addTower(School obj) {
        repository.add(obj);
    }

    public void delete(School obj) {
        repository.delete(obj);
    }

    public School findTower(String name) {
        return repository.find(name);
    }

    public void update(School school) {
        repository.update(school);
    }

    public void refresh(School school){
        repository.refresh(school);
    }

    public ArrayList<School> getLowerThan(int high){
        return repository.getLowerThan(high);
    }

    public ArrayList<School> getBiggerThan(int influence){
        return repository.getBiggerThan(influence);
    }
}
