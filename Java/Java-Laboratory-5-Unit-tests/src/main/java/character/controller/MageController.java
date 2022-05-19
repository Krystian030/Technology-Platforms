package character.controller;

import character.entity.Mage;
import character.repository.MageRepository;

import java.util.Optional;

public class MageController {

    private MageRepository mageRepository;

    public MageController(MageRepository mageRepository) {
        this.mageRepository = mageRepository;
    }

    public String find(String name){
        Optional<Mage> mage =  mageRepository.find(name);
        if (mage.isEmpty()) return "not found";
        else return mage.get().toString();
    }

    public String delete(String name) {
        try{
            mageRepository.delete(name);
            return "done";
        }
         catch(IllegalArgumentException ex) {
            return "not found";
         }
    }

    public String save(String name, int level) {
        Mage mage = Mage.builder()
                .name(name)
                .level(level)
                .build();
        try {
            mageRepository.save(mage);
            return "done";
        }
        catch(IllegalArgumentException ex) {
            return "bad request";
        }
    }

}
