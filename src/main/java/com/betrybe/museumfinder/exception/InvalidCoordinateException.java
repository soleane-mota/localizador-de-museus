package com.betrybe.museumfinder.exception;

/**
 * InvalidCoordinateException class.
 */
public class InvalidCoordinateException extends RuntimeException {

  /**
   * Constructs a new runtime exception with the specified detail message.
   */
  public InvalidCoordinateException() {
    super("Coordenada inválida!");
  }
}