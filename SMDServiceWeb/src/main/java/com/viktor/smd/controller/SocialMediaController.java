package com.viktor.smd.controller;

import com.viktor.smd.service.SocialMediaService;
import com.viktor.smd.socialMedia.v1.model.Account;
import com.viktor.smd.socialMedia.v1.service.SocialMediaApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SocialMediaController implements SocialMediaApi {

  private final SocialMediaService socialMediaService;

  /**
   * The constructor with mandatory parameter.
   *
   * @param socialMediaService the social media service.
   */
  public SocialMediaController(SocialMediaService socialMediaService) {
    this.socialMediaService = socialMediaService;
  }

  @Override
  public ResponseEntity<List<Account>> getSocialMediaAccounts() {
    return ResponseEntity.ok(socialMediaService.getAllAccounts());
  }
}
