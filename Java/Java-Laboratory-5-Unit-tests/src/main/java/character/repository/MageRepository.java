package character.repository;

import character.entity.Mage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import lombok.extern.java.Log;

@Log
public class MageRepository {
    private Collection<Mage> collection;

    public MageRepository(){
        collection = new ArrayList<>();
    }

    public MageRepository(ArrayList<Mage> mages){
        collection = mages;
    }

    public Optional<Mage> find(String name) {
        Optional<Mage> entity = collection.stream()
                .filter(value -> name.equals(value.getName()))
                .findFirst();
        return entity;
    }

    public void delete(String name) {
       Optional<Mage> entity = find(name);
        try {
            if(!entity.isEmpty()) {
                collection.remove(entity.get());
            }
            else {
                throw new IllegalArgumentException("IllegalArgumentException");
            }
        }
        catch(IllegalArgumentException ex) {
            log.warning(ex.getMessage());
            throw new IllegalArgumentException("IllegalArgumentException");
        }
    }

    public void save(Mage mage) {
        Optional<Mage> entity = find(mage.getName());
        try {
            if(entity.isEmpty()) {
                collection.add(mage);
            }
            else {
                throw new IllegalArgumentException("IllegalArgumentException");
            }
        }
        catch (IllegalArgumentException ex){
            log.warning(ex.getMessage());
            throw new IllegalArgumentException("IllegalArgumentException");
        }
    }

}
