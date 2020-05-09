package com.viktor.ttt.service.impl;

import com.viktor.ttt.model.User;
import com.viktor.ttt.repository.UserRepository;
import com.viktor.ttt.resource.UserDetailsResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of {@link UserDetailsService}.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserRepository userRepository;

  /**
   * The constructor with the mandatory parameter.
   *
   * @param userRepository the user repository.
   */
  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> optionalUser = userRepository.findByUsername(username);
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      return new UserDetailsResource(user);
    }

    throw new UsernameNotFoundException(String.format("User %s not found", username));
  }
}
