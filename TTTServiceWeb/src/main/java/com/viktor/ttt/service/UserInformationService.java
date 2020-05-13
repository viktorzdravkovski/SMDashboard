package com.viktor.ttt.service;

import com.viktor.ttt.taskTracker.v1.model.CurrentUserResponse;

/**
 * Service for User Information.
 */
public interface UserInformationService {

  /**
   * Gets information about the current logged in user.
   *
   * @return {@link CurrentUserResponse}.
   */
  CurrentUserResponse getCurrentUserInformation();
}
