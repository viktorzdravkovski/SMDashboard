package com.viktor.ttt.controller;

import com.viktor.ttt.service.UserInformationService;
import com.viktor.ttt.taskTracker.v1.model.CurrentUserResponse;
import com.viktor.ttt.taskTracker.v1.service.UserInformationApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * The REST controller for the User Information API.
 */
@RestController
public class UserInformationController implements UserInformationApi {

  private UserInformationService userInformationService;

  /**
   * The constructor with the mandatory parameter.
   *
   * @param userInformationService the user information service.
   */
  public UserInformationController(UserInformationService userInformationService) {
    this.userInformationService = userInformationService;
  }

  @Override
  public ResponseEntity<CurrentUserResponse> getCurrentUser() {
    CurrentUserResponse currentUserInformation = userInformationService.getCurrentUserInformation();
    return ResponseEntity.ok(currentUserInformation);
  }
}
