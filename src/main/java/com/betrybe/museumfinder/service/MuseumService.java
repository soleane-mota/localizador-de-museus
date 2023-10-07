package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MuseumService class.
 */
@Service
public class MuseumService implements MuseumServiceInterface {

  private final MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  public MuseumService(MuseumFakeDatabase museumFakeDatabase) {
    this.museumFakeDatabase = museumFakeDatabase;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    CoordinateUtil coordinateUtil = new CoordinateUtil();

    if (!coordinateUtil.isCoordinateValid(coordinate)) {
      throw new InvalidCoordinateException();
    }

    Optional<Museum> closesMuseum = museumFakeDatabase.getClosestMuseum(coordinate, maxDistance);

    if (closesMuseum.isEmpty()) {
      throw new MuseumNotFoundException();
    }

    return closesMuseum.get();
  }

  @Override
  public Museum createMuseum(Museum museum) {
    CoordinateUtil coordinateUtil = new CoordinateUtil();

    if (!coordinateUtil.isCoordinateValid(museum.getCoordinate())) {
      throw new InvalidCoordinateException();
    }

    return museumFakeDatabase.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    Optional<Museum> optionalMuseum = museumFakeDatabase.getMuseum(id);

    if (optionalMuseum.isEmpty()) {
      throw new MuseumNotFoundException();
    }

    return optionalMuseum.get();
  }
}
