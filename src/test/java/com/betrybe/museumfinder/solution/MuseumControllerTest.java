package com.betrybe.museumfinder.solution;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MuseumControllerTest {

  @MockBean
  MuseumService museumService;

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("Testa se retorna o objeto correto")
  public void testGetMuseum() throws Exception {
    Museum mockMuseum = new Museum();
    mockMuseum.setId(3769L);

    Mockito.when(museumService.getMuseum(any())).thenReturn(mockMuseum);

    Long id = mockMuseum.getId();

    String url = String.format("/museums/%d", id);

    mockMvc.perform(get(url))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id))
        .andExpect(jsonPath("$.name").value(mockMuseum.getName()))
        .andExpect(jsonPath("$.description").value(mockMuseum.getDescription()))
        .andExpect(jsonPath("$.address").value(mockMuseum.getName()))
        .andExpect(jsonPath("$.collectionType").value(mockMuseum.getName()))
        .andExpect(jsonPath("$.subject").value(mockMuseum.getName()))
        .andExpect(jsonPath("$.url").value(mockMuseum.getName()))
        .andExpect(jsonPath("$.coordinate").value(mockMuseum.getName()))
        .andExpect(jsonPath("$.legacyId").doesNotExist());
  }

  @Test
  @DisplayName("Testa se retorna not found")
  public void testNotFoundGetMuseum() throws Exception {
    Mockito.when(museumService.getMuseum(any())).thenThrow(
        MuseumNotFoundException.class
    );

    mockMvc.perform(get("/museums/3770"))
        .andExpect(status().isNotFound());
  }
}
