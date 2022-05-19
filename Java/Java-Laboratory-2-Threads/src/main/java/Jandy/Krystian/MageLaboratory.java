package Jandy.Krystian;
import Jandy.Krystian.character.entity.comparator.MageByLevelComparator;
import Jandy.Krystian.character.initialize.TestInitialize;
import Jandy.Krystian.character.repository.MageRepository;
import Jandy.Krystian.character.service.MageService;
import Jandy.Krystian.character.view.MageView;
import Jandy.Krystian.view.View;

public class MageLaboratory {

    public static void main(String[] args) {
        String argument = (args.length == 1) ? args[0] : null;
        MageLaboratory application = new MageLaboratory();

        MageService mageService = application.createMageService(argument);
        TestInitialize testInitialize = new TestInitialize(mageService);
        testInitialize.init();

        View mageView = new MageView(mageService);
        mageView.display();
    }

    public MageService createMageService(String argument){
        if (argument == null) {
            return new MageService(new MageRepository(),argument);
        }
        else if (argument.equalsIgnoreCase("no sort")) {
            return new MageService(new MageRepository(false),argument);
        }
        else if (argument.equalsIgnoreCase("sort")) {
            return new MageService(new MageRepository(true),argument);
        }
        else if (argument.equalsIgnoreCase("alternative sort")) {
            return new MageService(new MageRepository(new MageByLevelComparator()),argument);
        }
        else {
            return new MageService(new MageRepository(),argument);
        }
    }
}


