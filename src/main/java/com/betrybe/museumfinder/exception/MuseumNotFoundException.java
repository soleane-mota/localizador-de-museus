package com.betrybe.museumfinder.exception;

/**
 * MuseumNotFoundException class.
 */
public class MuseumNotFoundException extends RuntimeException {

  /**
   * Constructs a new runtime exception with the specified detail message.
   */
  public MuseumNotFoundException() {
    super("Museu não encontrado!");
  }
}
