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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeControllerTest {

  @MockBean
  CollectionTypeService collectionTypeService;

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("1 - Testa getCollectionTypesCount")
  public void testGetCollectionTypesCount() throws Exception {
    String[] typeList = {"arqueologia"};
    CollectionTypeCount collectionTypeCount = new CollectionTypeCount(typeList, 84L);

    Mockito.when(collectionTypeService.countByCollectionTypes(any())).thenReturn(
        collectionTypeCount
    );

    String collectionType = collectionTypeCount.collectionTypes()[0];
    String url = "/collections/count/arqueologia";

    mockMvc.perform(MockMvcRequestBuilders.get(url))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.collectionTypes").value(collectionType))
        .andExpect(jsonPath("$.count").value(collectionTypeCount.count()));
  }
}
