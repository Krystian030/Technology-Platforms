package Jandy.Krystian.character.view;


import Jandy.Krystian.character.entity.Mage;
import Jandy.Krystian.character.service.MageService;
import Jandy.Krystian.view.View;

import java.util.Map;

public class MageView implements View {

    private final MageService mageService;

    public MageView(MageService mageService) {
        this.mageService = mageService;
    }

    @Override
    public void display() {

        System.out.println("==========================================\nWidok: ");
        for (Mage mage : mageService.getAllMage()) {
            displayApprentices(mage,"-");
        }
        System.out.println("==========================================\n");

        System.out.println("==========================================\nStatystyki: ");
        for(Map.Entry<Mage,Integer> m : mageService.createMageServiceStatistics().entrySet()){
            System.out.println(m.getKey() + " : " + m.getValue());
        }
        System.out.println("==========================================\n");

    }

    public void displayApprentices(Mage mage,String indentation) {

        System.out.println(indentation +  mage);
        if (!mage.getApprentices().isEmpty()) {
            for (Mage m : mage.getApprentices()) {
                displayApprentices(m,indentation+"-");
            }
        }

    }
}
