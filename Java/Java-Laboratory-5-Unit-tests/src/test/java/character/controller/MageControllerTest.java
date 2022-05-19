package character.controller;

import character.entity.Mage;
import character.repository.MageRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MageControllerTest {

    @Test
    public void Delete_NotExistObject_NotFound() {

        // Given:
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);

        // When:
        when(mageRepository.find("mage")).thenReturn(Optional.empty());
        doThrow(new IllegalArgumentException("IllegalArgumentException")).when(mageRepository).delete("mage");
        String result = mageController.delete("mage");

        // Then:
        assertThat(result).isEqualTo("not found");
        verify(mageRepository,times(1)).delete(eq("mage"));
    }


    @Test
    public void Delete_ExistObject_Done() {

        // Given:
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);

        // When:
        when(mageRepository.find("mage")).thenReturn(Optional.of(Mage.builder().name("mage").level(10).build()));
        String result = mageController.delete("mage");

        // Then:
        assertThat(result).isEqualTo("done");
        verify(mageRepository,times(1)).delete(eq("mage"));
    }

    @Test
    public void Find_NotExistObject_NotFound(){

        // Given:
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);

        // When:
        when(mageRepository.find("mage")).thenReturn(Optional.empty());
        String result = mageController.find("mage");

        // Then:
        assertThat(result).isEqualTo("not found");
        verify(mageRepository,times(1)).find(eq("mage"));
    }

    @Test
    public void Find_ExistObject_StringEntityObject(){
        // Given:
        Mage mage = Mage.builder()
                .name("mage")
                .level(10)
                .build();
        String resultExpect = mage.toString();

        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);

        // When:
        when(mageRepository.find("mage")).thenReturn(Optional.of(mage));
        String result = mageController.find("mage");

        // Then:
        assertThat(result).isEqualTo(resultExpect);
        verify(mageRepository,times(1)).find(eq("mage"));
    }

    @Test
    public void Save_NewObject_Done(){

        // Given:
        Mage mage = Mage.builder()
                .name("mage")
                .level(10)
                .build();

        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);

        // When:
        when(mageRepository.find("mage")).thenReturn(Optional.empty());
        String result = mageController.save(mage.getName(),mage.getLevel());

        // Then:
        assertThat(result).isEqualTo("done");
        verify(mageRepository,times(1)).save(eq(mage));
    }

    @Test
    public void Save_ExistObject_BadRequest(){
        // Given:
        MageRepository mageRepository = mock(MageRepository.class);
        MageController mageController = new MageController(mageRepository);
        Mage mage = Mage.builder()
                        .name("mage")
                        .level(99)
                        .build();

        // When:
        doThrow(new IllegalArgumentException("IllegalArgumentException")).when(mageRepository).save(mage);
        when(mageRepository.find("mage")).thenReturn(Optional.of(mage));

        String result = mageController.save("mage",99);

        // Then:
        assertThat(result).isEqualTo("bad request");
        verify(mageRepository,times(1)).save(eq(mage));
    }

}
