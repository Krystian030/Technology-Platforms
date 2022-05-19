package Jandy.Krystian.character.repository;

import Jandy.Krystian.character.entity.Mage;
import Jandy.Krystian.character.entity.comparator.MageByLevelComparator;
import Jandy.Krystian.character.service.MageService;
import Jandy.Krystian.repository.MemoryRepository;

import java.util.Comparator;

public class MageRepository extends MemoryRepository<Mage> {


    public MageRepository() {
    }

    public MageRepository(boolean sort) {
        super(sort);
    }

    public MageRepository(Comparator comparator) {
        super(comparator);
    }

}
