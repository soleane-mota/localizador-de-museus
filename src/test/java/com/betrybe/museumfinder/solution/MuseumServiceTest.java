package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class MuseumServiceTest {

  @MockBean
  MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  MuseumService museumService;

  @Test
  @DisplayName("1 - Testa se retornar objeto")
  public void testGetMuseumSuccess() {
    Museum mockMuseum = new Museum();

    Mockito.when(museumFakeDatabase.getMuseum(any())).thenReturn(
        Optional.of(mockMuseum)
    );

    Long id = mockMuseum.getId();

    Museum museum = museumService.getMuseum(id);

    assertNotNull(museum);
    assertEquals(id, museum.getId());
    assertEquals(mockMuseum.getName(), museum.getName());
    assertEquals(mockMuseum.getCoordinate(), museum.getCoordinate());
    assertEquals(mockMuseum.getCollectionType(), museum.getCollectionType());
    assertEquals(mockMuseum.getDescription(), museum.getDescription());
    assertEquals(mockMuseum.getAddress(), museum.getAddress());
    assertEquals(mockMuseum.getUrl(), museum.getUrl());
    assertEquals(mockMuseum.getSubject(), museum.getSubject());

    Mockito.verify(museumFakeDatabase).getMuseum(eq(id));
  }

  @Test
  @DisplayName("Testa se lança exceção")
  public void testNotFoundGetMedic() {
    Mockito.when(museumFakeDatabase.getMuseum(any())).thenReturn(
        Optional.empty()
    );

    assertThrows(MuseumNotFoundException.class,
        () -> museumService.getMuseum(3770L)
    );
  }
}
