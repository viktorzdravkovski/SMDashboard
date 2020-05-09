package com.viktor.ttt.util;

import com.viktor.ttt.dto.UserDto;
import com.viktor.ttt.model.Role;
import com.viktor.ttt.model.User;
import com.viktor.ttt.model.UserDetails;

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
   * @param userDto  the user dto.
   * @param roleList the list of roles.
   * @return a mapped {@link User}.
   */
  public static User mapUserFromUserDto(UserDto userDto, List<Role> roleList) {

    String firstName = userDto.getName();
    String lastName = userDto.getLastName();
    String email = userDto.getEmail();
    String username = userDto.getUsername();
    String password = userDto.getPassword();

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
