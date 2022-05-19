package character.repository;

import character.entity.Mage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MageRepositoryTest {

    @Test
    public void Find_NotExistObject_OptionalEmpty() {

        // Given:
        MageRepository mageRepository = new MageRepository();

        // When:
        Optional<Mage> mage = mageRepository.find("Mage");

        // Then:
        assertThat(mage).isEqualTo(Optional.empty())
                .as("Should be Optional Empty");


    }

    @Test
    public void Find_ExistObject_OptionalObject() {

        // Given:
        ArrayList<Mage> mages = new ArrayList<>();
        Mage mage = Mage.builder()
                .name("Mage")
                .level(399)
                .build();
        mages.add(mage);

        MageRepository mageRepository = new MageRepository(mages);

        // When:
        Optional<Mage> mageCheck = mageRepository.find("Mage");

        // Then:
        assertThat(mageCheck).isEqualTo(Optional.of(mage))
                .as("Should be equal");
    }

    @Test
    public void Delete_NotExistsObject_IllegalArgumentException() {

        // Given:
        MageRepository mageRepository = new MageRepository();

        // When:
        Throwable thrown = catchThrowable(()->{
            mageRepository.delete("Mage");
        });

        // Then
        assertThat(thrown).hasMessage("IllegalArgumentException");
    }

    @Test
    public void Save_SaveExistsObject_IllegalArgumentException() {

        // Given:
        ArrayList<Mage> mages = new ArrayList<>();
        Mage mage = Mage.builder()
                .name("Mage")
                .level(399)
                .build();
        mages.add(mage);
        MageRepository mageRepository = new MageRepository(mages);
        // When:
        Throwable thrown = catchThrowable(()->{
            mageRepository.save(mage);
        });

        // Then
        assertThat(thrown).hasMessage("IllegalArgumentException");
    }
}
