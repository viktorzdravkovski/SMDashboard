package com.viktor.ttt.util;

import com.viktor.ttt.model.Role;
import com.viktor.ttt.model.User;
import com.viktor.ttt.model.UserDetails;
import com.viktor.ttt.taskTracker.v1.model.RegisterUserRequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class for user mapping.
 */
public final class UserMapperFactory {

  /**
   * Private constructor to prevent instantiation.
   */
  private UserMapperFactory() {
    throw new AssertionError("Do not instantiate.");
  }

  /**
   * Maps a user from the User dto.
   *
   * @param userRequestBody the user request body.
   * @param roleList        the list of roles.
   * @return a mapped {@link User}.
   */
  public static User mapUserFromRegisterUserRequest(RegisterUserRequestBody userRequestBody, List<Role> roleList) {

    String firstName = userRequestBody.getFirstName();
    String lastName = userRequestBody.getLastName();
    String email = userRequestBody.getEmail();
    String username = userRequestBody.getUsername();
    String password = userRequestBody.getPassword();

    UserDetails userDetails = new UserDetails();
    userDetails.setFirstName(firstName);
    userDetails.setLastName(lastName);
    userDetails.setEmail(email);

    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    user.setActive(true);
    user.setTasks(new ArrayList<>());
    user.setRoles(roleList);
    user.setUserDetails(userDetails);

    return user;
  }
}
