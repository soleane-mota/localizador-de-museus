package com.betrybe.museumfinder.solution;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeControllerTest {

  @MockBean
  CollectionTypeService collectionTypeService;

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("1 - Testa caso de sucesso getCollectionTypesCount")
  public void testGetCollectionTypesCount() throws Exception {
    String[] typeList = {"arqueologia"};
    CollectionTypeCount collectionTypeCount = new CollectionTypeCount(typeList, 84L);

    Mockito.when(collectionTypeService.countByCollectionTypes(any())).thenReturn(
        collectionTypeCount
    );

    String collectionType = collectionTypeCount.collectionTypes()[0];
    String url = "/collections/count/arqueologia";

    mockMvc.perform(get(url))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.collectionTypes").value(collectionType))
        .andExpect(jsonPath("$.count").value(collectionTypeCount.count()));
  }

  @Test
  @DisplayName("2 - Testa se collectionType não for encontrado")
  public void testNotFoundGetCollectionTypesCount() throws Exception {
    String[] typeList = {""};
    CollectionTypeCount collectionTypeCount = new CollectionTypeCount(typeList, 0L);

    Mockito.when(collectionTypeService.countByCollectionTypes(any())).thenReturn(
        collectionTypeCount
    );

    mockMvc.perform(get("/collections/count/"))
        .andExpect(status().isNotFound());
  }
}
