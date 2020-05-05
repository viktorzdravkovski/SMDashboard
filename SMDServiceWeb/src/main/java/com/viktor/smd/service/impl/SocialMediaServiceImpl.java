package com.viktor.smd.service.impl;

import com.viktor.smd.repository.UserRepository;
import com.viktor.smd.service.SocialMediaService;
import com.viktor.smd.socialMedia.v1.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of {@link SocialMediaService}.
 */
@Service
public class SocialMediaServiceImpl implements SocialMediaService {

  @Autowired
  private final UserRepository userRepository;

  /**
   * The constructor with mandatory parameter.
   *
   * @param userRepository the user repository.
   */
  public SocialMediaServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<Account> getAllAccounts() {
    return null;
  }
}
