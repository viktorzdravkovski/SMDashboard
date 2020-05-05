package com.viktor.smd.service;

import com.viktor.smd.socialMedia.v1.model.Account;

import java.util.List;

/**
 * Service for social media interaction.
 */
public interface SocialMediaService {

  /**
   * Get all accounts.
   *
   * @return a list of {@link Account}.
   */
  List<Account> getAllAccounts();
}
