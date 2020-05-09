package com.viktor.ttt.service;

import com.viktor.ttt.dto.UserDto;
import com.viktor.ttt.resource.AuthenticationResponse;
import com.viktor.ttt.exception.UsernameAlreadyExistsException;

/**
 * Service for logging in and registering the user.
 */
public interface UserAuthenticationService {

  /**
   * Registers the received user.
   *
   * @param userDto the user dto.
   * @return {@link AuthenticationResponse}.
   * @throws {@link UsernameAlreadyExistsException}.
   */
  AuthenticationResponse registerUser(UserDto userDto) throws UsernameAlreadyExistsException;
}
