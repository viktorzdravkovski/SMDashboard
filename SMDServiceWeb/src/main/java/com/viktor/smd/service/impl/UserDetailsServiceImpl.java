package com.viktor.smd.service.impl;

import com.viktor.smd.model.User;
import com.viktor.smd.repository.UserRepository;
import com.viktor.smd.resource.UserDetailsResource;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private UserRepository userRepository;

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
