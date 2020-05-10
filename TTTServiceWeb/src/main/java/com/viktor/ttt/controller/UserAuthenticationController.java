package com.viktor.ttt.controller;

import com.viktor.ttt.service.UserAuthenticationService;
import com.viktor.ttt.taskTracker.v1.model.LoginConfirmation;
import com.viktor.ttt.taskTracker.v1.model.LoginUserRequestBody;
import com.viktor.ttt.taskTracker.v1.model.RegisterUserRequestBody;
import com.viktor.ttt.taskTracker.v1.model.RegistrationConfirmation;
import com.viktor.ttt.taskTracker.v1.service.AuthenticationApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The REST controller for user authentication.
 */
@RestController
public class UserAuthenticationController implements AuthenticationApi {

  private final UserAuthenticationService userAuthenticationService;

  /**
   * The constructor with the mandatory parameter.
   *
   * @param userAuthenticationService the user authentication service.
   */
  public UserAuthenticationController(
      UserAuthenticationService userAuthenticationService) {
    this.userAuthenticationService = userAuthenticationService;
  }

  @Override
  public ResponseEntity<RegistrationConfirmation> registerUser(@RequestBody RegisterUserRequestBody body) {
    RegistrationConfirmation registrationConfirmation = userAuthenticationService.registerUser(body);
    return ResponseEntity.ok(registrationConfirmation);
  }

  @Override
  public ResponseEntity<LoginConfirmation> loginUser(@RequestBody LoginUserRequestBody body) {
    LoginConfirmation loginConfirmation = userAuthenticationService.loginUser(body);
    return ResponseEntity.ok(loginConfirmation);
  }
}
