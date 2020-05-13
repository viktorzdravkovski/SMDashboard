package com.viktor.ttt.service.impl;

import com.viktor.ttt.model.Role;
import com.viktor.ttt.model.User;
import com.viktor.ttt.model.UserDetails;
import com.viktor.ttt.repository.UserRepository;
import com.viktor.ttt.service.UserInformationService;
import com.viktor.ttt.taskTracker.v1.model.CurrentUserResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of {@link UserInformationService}.
 */
@Service
public class UserInformationServiceImpl implements UserInformationService {

  private UserRepository userRepository;
  private HttpServletRequest httpServletRequest;

  /**
   * The constructor with the mandatory parameter.
   *
   * @param userRepository     the user repository.
   * @param httpServletRequest the httpServletRequest.
   */
  public UserInformationServiceImpl(UserRepository userRepository, HttpServletRequest httpServletRequest) {
    this.userRepository = userRepository;
    this.httpServletRequest = httpServletRequest;
  }

  @Override
  public CurrentUserResponse getCurrentUserInformation() {

    Optional<String> optionalUsername = Optional.ofNullable(httpServletRequest.getUserPrincipal()).map(Principal::getName);
    if (optionalUsername.isPresent()) {

      String username = optionalUsername.get();
      User user = userRepository.getByUsername(username);
      return mapCurrentUserResponse(user);
    }

    return null;
  }

  private CurrentUserResponse mapCurrentUserResponse(User user) {

    UserDetails userDetailsResource = user.getUserDetails();
    String firstName = userDetailsResource.getFirstName();
    String lastName = userDetailsResource.getLastName();
    String fullName = String.format("%s %s", firstName, lastName);
    String username = user.getUsername();

    UserDetails userDetails = user.getUserDetails();
    String email = userDetails.getEmail();

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
