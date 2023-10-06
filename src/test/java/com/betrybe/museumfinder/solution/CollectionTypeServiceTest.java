package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CollectionTypeServiceTest {

  @MockBean
  MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  CollectionTypeService collectionTypeService;

  @Test
  @DisplayName("1 - Testa se conta museu pelo tipo")
  public void testCountByCollectionTypes() {
    Mockito.when(museumFakeDatabase.countByCollectionType(any())).thenReturn(84L);

    CollectionTypeCount collectionTypeCount = collectionTypeService.countByCollectionTypes(
        "arqueologia");
    String collectionType = collectionTypeCount.collectionTypes()[0];

    assertNotNull(collectionTypeCount);
    assertEquals("arqueologia", collectionType);
    assertEquals(84L, collectionTypeCount.count());

    Mockito.verify(museumFakeDatabase).countByCollectionType(eq("arqueologia"));
  }
}
