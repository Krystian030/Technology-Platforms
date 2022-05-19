import character.controller.MageController;
import character.entity.Mage;
import character.repository.MageRepository;

import java.util.Optional;

public class Laboratory5 {

    public static void main(String[] args){
        MageController mageController = new MageController(new MageRepository());
        Mage a = Mage.builder()
                .level(10)
                .name("A")
                .build();

        String out;
        out = mageController.save("A",10);
        System.out.println(out);

        out = mageController.find("A");
        System.out.println(out);

        out = mageController.delete("A");
        System.out.println(out);

        out = mageController.delete("A");
        System.out.println(out);

    }

}

