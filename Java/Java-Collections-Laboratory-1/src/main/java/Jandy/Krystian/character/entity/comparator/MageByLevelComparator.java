package Jandy.Krystian.character.entity.comparator;

import Jandy.Krystian.character.entity.Mage;

import java.util.Comparator;

public class MageByLevelComparator implements Comparator<Mage> {

    @Override
    public int compare(Mage m1, Mage m2){
        int ret = m2.getLevel() - m1.getLevel();
        if(ret == 0) 
            ret = m1.getName() == null 
                    ? (m2.getName() == null ? 0 : 1) 
                    : m1.getName().compareTo(m2.getName());
        
        if (ret == 0) ret = Double.compare(m1.getPower(),m2.getPower());
        
        return ret;
    }
}
