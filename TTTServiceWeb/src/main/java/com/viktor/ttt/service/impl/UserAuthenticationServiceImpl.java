package com.viktor.ttt.service.impl;

import com.viktor.ttt.exception.UsernameAlreadyExistsException;
import com.viktor.ttt.model.Role;
import com.viktor.ttt.model.RoleType;
import com.viktor.ttt.model.User;
import com.viktor.ttt.repository.RoleRepository;
import com.viktor.ttt.repository.UserRepository;
import com.viktor.ttt.service.UserAuthenticationService;
import com.viktor.ttt.taskTracker.v1.model.LoginConfirmation;
import com.viktor.ttt.taskTracker.v1.model.LoginUserRequestBody;
import com.viktor.ttt.taskTracker.v1.model.RegisterUserRequestBody;
import com.viktor.ttt.taskTracker.v1.model.RegistrationConfirmation;
import com.viktor.ttt.util.UserMapperFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

/**
 * Implementation of {@link UserAuthenticationService}.
 */
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final AuthenticationManager authenticationManager;

  /**
   * The constructor with the mandatory parameters.
   *
   * @param userRepository        the user repository.
   * @param roleRepository        the role repository.
   * @param authenticationManager the authentication manager.
   */
  public UserAuthenticationServiceImpl(
      UserRepository userRepository,
      RoleRepository roleRepository,
      AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.authenticationManager = authenticationManager;
  }

  @Override
  public RegistrationConfirmation registerUser(RegisterUserRequestBody body) throws UsernameAlreadyExistsException {

    String username = body.getUsername();
    Optional<User> optionalUser = userRepository.findByUsername(username);
    if (optionalUser.isPresent()) {
      throw new UsernameAlreadyExistsException(username);
    }

    Role role = roleRepository.getRoleByRoleName(RoleType.REGISTERED);
    User user = UserMapperFactory.mapUserFromRegisterUserRequest(body, Collections.singletonList(role));

    userRepository.save(user);

    RegistrationConfirmation registrationConfirmation = new RegistrationConfirmation();
    registrationConfirmation.setMessage("Registration successful");
    registrationConfirmation.setStatus(HttpStatus.OK.value());

    return registrationConfirmation;
  }

  @Override
  public LoginConfirmation loginUser(LoginUserRequestBody userLoginDto) {

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword());
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    boolean isAuthenticated = isAuthenticated(authentication);
    if (isAuthenticated) {
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    LoginConfirmation loginConfirmation = new LoginConfirmation();
    loginConfirmation.setMessage("Login successful");
    loginConfirmation.setStatus(HttpStatus.OK.value());

    return loginConfirmation;
  }

  private boolean isAuthenticated(Authentication authentication) {
    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
  }
}
