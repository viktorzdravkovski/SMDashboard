package com.viktor.ttt.controller;

import com.viktor.ttt.dto.UserDto;
import com.viktor.ttt.resource.AuthenticationResponse;
import com.viktor.ttt.service.UserAuthenticationService;
import com.viktor.ttt.service.exception.UsernameAlreadyExistsException;
import com.viktor.ttt.util.ResponseMessageFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller for user authentication.
 */
@Controller
@RequestMapping("authenticate")
public class UserAuthenticationController {

  private final UserAuthenticationService userAuthenticationService;

  /**
   * The constructor with the mandatory parameter.
   *
   * @param userAuthenticationService the user authentication service.
   */
  public UserAuthenticationController(UserAuthenticationService userAuthenticationService) {
    this.userAuthenticationService = userAuthenticationService;
  }

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(UserDto userDto) {
    try {
      AuthenticationResponse response = userAuthenticationService.registerUser(userDto);
      return ResponseEntity.ok(response);
    } catch (UsernameAlreadyExistsException e) {
      return ResponseEntity.badRequest().body(ResponseMessageFactory.createUsernameAlreadyExistsResponseMessage());
    }
  }
}
