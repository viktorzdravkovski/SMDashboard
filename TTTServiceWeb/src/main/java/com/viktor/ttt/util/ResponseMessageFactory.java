package com.viktor.ttt.util;

import com.viktor.ttt.resource.AuthenticationResponse;
import org.springframework.http.HttpStatus;

/**
 * Factory class for mapping response messages.
 */
public final class ResponseMessageFactory {

  /**
   * Private constructor to prevent instantiation.
   */
  private ResponseMessageFactory() {
    throw new AssertionError("Do not instantiate.");
  }

  /**
   * Maps a response message for when a username already exists.
   *
   * @return a mapped {@link AuthenticationResponse}.
   */
  public static AuthenticationResponse createUsernameAlreadyExistsResponseMessage() {

    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    authenticationResponse.setMessage("Username already exists.");
    authenticationResponse.setStatus(HttpStatus.PRECONDITION_FAILED);

    return authenticationResponse;
  }
}
