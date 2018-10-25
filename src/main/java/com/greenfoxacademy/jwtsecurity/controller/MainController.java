package com.greenfoxacademy.jwtsecurity.controller;

import com.greenfoxacademy.jwtsecurity.models.Client;
import com.greenfoxacademy.jwtsecurity.security.ClientDetailsService;
import com.greenfoxacademy.jwtsecurity.service.ClientService;
import com.greenfoxacademy.jwtsecurity.service.RoleService;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;

@Controller
public class MainController {


  @Autowired
  RoleService roleService;
  @Autowired
  ClientService clientService;

  @GetMapping("/login")
  public String getLoginPage() {
    return "login";
  }

  @PostMapping("/signin")
  public void submitLoginPage() {
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/homepage")
  public String getHomepage() {
    return "homepage";
  }

  @GetMapping("/register")
  public String getRegisterPage() {
    return "register";
  }

  @PostMapping("/register")
  public String submitRegisterPage(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
    Client client = new Client(username, password, new HashSet<>());
    client.addNewRole(roleService.findRoleById(1L));
    clientService.createNewClient(client);
    return "redirect:/login";
  }
}
