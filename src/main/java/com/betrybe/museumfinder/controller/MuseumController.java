package com.betrybe.museumfinder.controller;

import static com.betrybe.museumfinder.util.ModelDtoConverter.dtoToModel;
import static com.betrybe.museumfinder.util.ModelDtoConverter.modelToDto;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
  public ResponseEntity<MuseumDto> createMuseum(@RequestBody MuseumCreationDto newMuseum) {
    Museum museum = service.createMuseum(dtoToModel(newMuseum));

    return ResponseEntity.status(HttpStatus.CREATED).body(modelToDto(museum));
  }

  /**
   * Method to get museum by query string.
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getMuseum(
      @RequestParam double lat,
      @RequestParam double lng,
      @RequestParam("max_dist_km") Double maxDistance
  ) {
    Coordinate coordinate = new Coordinate(lat, lng);
    Museum museum = service.getClosestMuseum(coordinate, maxDistance);
    return ResponseEntity.ok(modelToDto(museum));
  }
}
