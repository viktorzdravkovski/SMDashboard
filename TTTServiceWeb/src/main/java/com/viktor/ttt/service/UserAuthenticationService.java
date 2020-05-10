package com.viktor.ttt.service;

import com.viktor.ttt.exception.UsernameAlreadyExistsException;
import com.viktor.ttt.taskTracker.v1.model.LoginConfirmation;
import com.viktor.ttt.taskTracker.v1.model.LoginUserRequestBody;
import com.viktor.ttt.taskTracker.v1.model.RegisterUserRequestBody;
import com.viktor.ttt.taskTracker.v1.model.RegistrationConfirmation;

/**
 * Service for logging in and registering the user.
 */
public interface UserAuthenticationService {

  /**
   * Registers the received user.
   *
   * @param registerUserRequestBody the register user request body.
   * @return {@link RegistrationConfirmation}.
   * @throws {@link UsernameAlreadyExistsException}.
   */
  RegistrationConfirmation registerUser(RegisterUserRequestBody registerUserRequestBody)
      throws UsernameAlreadyExistsException;

  /**
   * Login for the user.
   *
   * @param loginUserRequestBody the login user request body.
   * @return {@link LoginConfirmation}.
   */
  LoginConfirmation loginUser(LoginUserRequestBody loginUserRequestBody);
}
