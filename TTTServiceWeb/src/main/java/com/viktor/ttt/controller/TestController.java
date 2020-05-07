package com.viktor.ttt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/")
  public String hello() {
    return "Dis my homepage bruh";
  }

  @GetMapping("/test")
  public String testEndpoint() {
    return "TEST!";
  }
}
