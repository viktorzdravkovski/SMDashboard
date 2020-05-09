package com.viktor.ttt.exception;

/**
 * Exception for when a username is already taken.
 */
public class UsernameAlreadyExistsException extends RuntimeException {

  /**
   * The constructor with the mandatory parameter.
   *
   * @param username the username.
   */
  public UsernameAlreadyExistsException(String username) {
    super(String.format("User with username %s already exists.", username));
  }
}
