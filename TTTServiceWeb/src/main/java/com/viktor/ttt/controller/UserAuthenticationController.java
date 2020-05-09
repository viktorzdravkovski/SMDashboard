package com.viktor.ttt.controller;

import com.viktor.ttt.dto.UserDto;
import com.viktor.ttt.dto.UserLoginDto;
import com.viktor.ttt.resource.AuthenticationResponse;
import com.viktor.ttt.service.UserAuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The REST controller for user authentication.
 */
@RestController
@RequestMapping("authenticate")
public class UserAuthenticationController {

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

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(UserDto userDto) {
    AuthenticationResponse response = userAuthenticationService.registerUser(userDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody UserLoginDto userLoginDto) {
    AuthenticationResponse authenticationResponse = userAuthenticationService.loginUser(userLoginDto);
    return ResponseEntity.ok(authenticationResponse);
  }
}
