package com.greenfoxacademy.devmasecurity1.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
  @PreAuthorize("hasRole('USER')")
  @GetMapping("")
  public String getUserPage() {
    return "user";
  }
}
