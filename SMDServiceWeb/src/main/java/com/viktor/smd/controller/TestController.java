package com.viktor.smd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  @GetMapping("/")
  public String hello() {
    return "Dis ny homepage bruh";
  }

  @GetMapping("/test")
  public String testEndpoint() {
    return "TEST!";
  }
}
