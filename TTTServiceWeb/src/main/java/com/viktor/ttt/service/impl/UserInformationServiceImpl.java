package com.viktor.ttt.service.impl;

import com.viktor.ttt.model.Role;
import com.viktor.ttt.model.User;
import com.viktor.ttt.model.UserDetails;
import com.viktor.ttt.repository.UserRepository;
import com.viktor.ttt.resource.UserDetailsResource;
import com.viktor.ttt.service.UserInformationService;
import com.viktor.ttt.taskTracker.v1.model.CurrentUserResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link UserInformationService}.
 */
@Service
public class UserInformationServiceImpl implements UserInformationService {

  private UserRepository userRepository;

  /**
   * The constructor with the mandatory parameter.
   *
   * @param userRepository the user repository.
   */
  public UserInformationServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public CurrentUserResponse getCurrentUserInformation() {

    UserDetailsResource userDetailsResource =
        (UserDetailsResource) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return mapCurrentUserResponse(userDetailsResource);
  }

  private CurrentUserResponse mapCurrentUserResponse(UserDetailsResource userDetailsResource) {

    String username = userDetailsResource.getUsername();
    User user = userRepository.getByUsername(username);

    UserDetails userDetails = user.getUserDetails();
    String email = userDetails.getEmail();
    String firstName = userDetails.getFirstName();
    String lastName = userDetails.getLastName();
    String fullName = String.format("%s %s", firstName, lastName);

    List<Role> roles = user.getRoles();
    List<String> roleList = roles.stream()
        .map(role -> role.getRoleName().name())
        .collect(Collectors.toList());

    CurrentUserResponse currentUserResponse = new CurrentUserResponse();
    currentUserResponse.setEmail(email);
    currentUserResponse.setFullName(fullName);
    currentUserResponse.setUsername(username);
    currentUserResponse.setRoles(roleList);
    return currentUserResponse;
  }
}
