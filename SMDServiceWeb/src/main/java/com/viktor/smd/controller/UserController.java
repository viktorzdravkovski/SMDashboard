package com.viktor.smd.controller;

import com.viktor.smd.model.User;
import com.viktor.smd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping(path = "/add")
  public @ResponseBody
  String addNewUser(@RequestParam String username, @RequestParam String email) {

    User user = new User();
    user.setUsername(username);
    user.setEmail(email);
    userRepository.save(user);

    return "Saved";
  }

  @GetMapping(path = "/all")
  public @ResponseBody
  Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }
}
