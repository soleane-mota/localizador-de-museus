package com.betrybe.museumfinder.controller;

import static com.betrybe.museumfinder.util.ModelDtoConverter.dtoToModel;
import static com.betrybe.museumfinder.util.ModelDtoConverter.modelToDto;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MuseumController class.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {

  private final MuseumServiceInterface service;

  @Autowired
  public MuseumController(MuseumServiceInterface service) {
    this.service = service;
  }

  /**
   * Method to create a new museum.
   */
  @PostMapping()
  public ResponseEntity<MuseumDto> createMuseum(MuseumCreationDto newMuseum) {
    Museum museum = service.createMuseum(dtoToModel(newMuseum));

    return ResponseEntity.status(HttpStatus.CREATED).body(modelToDto(museum));
  }
}
