package com.viktor.ttt.service.impl;

import com.viktor.ttt.dto.UserDto;
import com.viktor.ttt.dto.UserLoginDto;
import com.viktor.ttt.model.Role;
import com.viktor.ttt.model.RoleType;
import com.viktor.ttt.model.User;
import com.viktor.ttt.repository.RoleRepository;
import com.viktor.ttt.repository.UserRepository;
import com.viktor.ttt.resource.AuthenticationResponse;
import com.viktor.ttt.service.UserAuthenticationService;
import com.viktor.ttt.exception.UsernameAlreadyExistsException;
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
   * @param userRepository the user repository.
   * @param roleRepository the role repository.
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
  public AuthenticationResponse registerUser(UserDto userDto) throws UsernameAlreadyExistsException {

    String username = userDto.getUsername();
    Optional<User> optionalUser = userRepository.findByUsername(username);
    if (optionalUser.isPresent()) {
      throw new UsernameAlreadyExistsException(username);
    }

    Role role = roleRepository.getRoleByRoleName(RoleType.REGISTERED);
    User user = UserMapperFactory.mapUserFromUserDto(userDto, Collections.singletonList(role));

    userRepository.save(user);

    AuthenticationResponse authenticationResponse = new AuthenticationResponse();
    authenticationResponse.setMessage("User registered successfully");
    authenticationResponse.setStatus(HttpStatus.OK);

    return authenticationResponse;
  }

  @Override
  public AuthenticationResponse loginUser(UserLoginDto userLoginDto) {

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword());
    Authentication authentication = authenticationManager.authenticate(authenticationToken);
    boolean isAuthenticated = isAuthenticated(authentication);
    if (isAuthenticated) {
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    AuthenticationResponse response = new AuthenticationResponse();
    response.setMessage("User login successfully");
    response.setStatus(HttpStatus.OK);

    return response;
  }

  private boolean isAuthenticated(Authentication authentication) {
    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
  }
}
