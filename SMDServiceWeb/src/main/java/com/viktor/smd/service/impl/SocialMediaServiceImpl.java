package com.viktor.smd.service.impl;

import com.viktor.smd.model.User;
import com.viktor.smd.repository.UserRepository;
import com.viktor.smd.service.SocialMediaService;
import com.viktor.smd.socialMedia.v1.model.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link SocialMediaService}.
 */
@Service
public class SocialMediaServiceImpl implements SocialMediaService {

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

    Iterable<User> allUsers = userRepository.findAll();
    Account account = new Account();
    List<Account> accounts = new ArrayList<>();

    allUsers.forEach(user -> {

      account.setId(user.getId());
      account.setName(user.getUsername());
      accounts.add(account);
    });

    return accounts;
  }
}
