package com.viktor.ttt.service.impl;

import com.viktor.ttt.dto.UserDto;
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

  /**
   * The constructor with the mandatory parameters.
   *
   * @param userRepository the user repository.
   * @param roleRepository the role repository.
   */
  public UserAuthenticationServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
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
}
