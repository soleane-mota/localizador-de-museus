package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * GeneralControllerAdvice class.
 */
@ControllerAdvice
public class GeneralControllerAdvice {

  @ExceptionHandler({InvalidCoordinateException.class})
  public ResponseEntity<String> handleBadRequest(RuntimeException exception) {
    return ResponseEntity.badRequest().body(exception.getMessage());
  }

  @ExceptionHandler({MuseumNotFoundException.class})
  public ResponseEntity<String> handleNotFound(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleInternalError(Exception exception) {
    return ResponseEntity.internalServerError().body("Erro interno!");
  }
}