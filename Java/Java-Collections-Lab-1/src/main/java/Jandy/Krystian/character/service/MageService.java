package Jandy.Krystian.character.service;

import Jandy.Krystian.character.entity.Mage;
import Jandy.Krystian.character.entity.comparator.MageByLevelComparator;
import Jandy.Krystian.character.repository.MageRepository;

import java.util.*;

public class MageService {
    private final MageRepository mageRepository;
    private final String option;

    public MageService( MageRepository mageRepository, String option) {
        this.mageRepository = mageRepository;
        this.option = option;
    }

    public List<Mage> getAllMage(){
        return mageRepository.getAll();
    }

    public String getOption() {
        return option;
    }

    public Set<Mage> createApprentices(){
        if (option.equalsIgnoreCase("no sort")) {
            return new HashSet<Mage>();
        }
        else if (option.equalsIgnoreCase("sort")) {
            return new TreeSet<Mage>();
        }
        else if (option.equalsIgnoreCase("alternative sort")) {
            return new TreeSet<Mage>(new MageByLevelComparator());
        }
        return new HashSet<Mage>();
    }

    public Map<Mage,Integer> createMapStatistics(){
        if (option.equalsIgnoreCase("no sort")) {
            return new HashMap<Mage,Integer>();
        }
        else if (option.equalsIgnoreCase("sort")) {
            return new TreeMap<Mage,Integer>();
        }
        else if (option.equalsIgnoreCase("alternative sort")) {
            return new TreeMap<Mage,Integer>(new MageByLevelComparator());
        }
        return new HashMap<Mage,Integer>();
    }

    public void add(Mage mage){
        mageRepository.add(mage);
    }

    public void delete(Mage mage){
        mageRepository.delete(mage);
    }

    public int count(Mage mage,Map<Mage,Integer> map, int counter){

        if (!mage.getApprentices().isEmpty()) {
            for (Mage m : mage.getApprentices()) {
                counter += count(m,map,0);
            }
        }
        map.put(mage,counter++);

        return counter;
    }

    public Map<Mage,Integer> createMageServiceStatistics(){
        Map<Mage,Integer> map = createMapStatistics();
        for(Mage mage : mageRepository.getAll()) {
            count(mage,map,0);
        }
        return map;
    }
}
