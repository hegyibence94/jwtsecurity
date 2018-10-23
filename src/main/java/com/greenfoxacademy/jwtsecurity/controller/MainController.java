package com.greenfoxacademy.jwtsecurity.controller;

import com.greenfoxacademy.jwtsecurity.models.Client;
import com.greenfoxacademy.jwtsecurity.security.JwtProvider;
import com.greenfoxacademy.jwtsecurity.service.ClientService;
import com.greenfoxacademy.jwtsecurity.service.RoleService;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

@Controller
public class MainController {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  PasswordEncoder encoder = passwordEncoder();
  @Autowired
  RoleService roleService;
  @Autowired
  ClientService clientService;


  @Bean
  public WebServerFactoryCustomizer customizer() {
    return container -> {
      if (container instanceof TomcatServletWebServerFactory) {
        TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) container;
        tomcat.addContextCustomizers(context -> context.setCookieProcessor(new LegacyCookieProcessor()));
      }
    };
  }

  @Autowired
  JwtProvider jwtProvider;

  @GetMapping("/login")
  public String getLoginPage() {
    return "login";
  }

  @PostMapping("/signin")
  public void submitLoginPage() {
  }

  @GetMapping("/homepage")
  public String getHomepage(Model model, HttpServletRequest request) {
    String token = jwtProvider.getJwtStringFromHeader(request);
    if (jwtProvider.validateJwtToken(token)) {
      model.addAttribute("jwtToken", jwtProvider.getUserNameFromJwtToken(token));
    } else {
      model.addAttribute("jwtToken", "Fail :(");
    }
    return "homepage";
  }

  @GetMapping("/register")
  public String getRegisterPage() {
    return "register";
  }

  @PostMapping("/register")
  public String submitRegisterPage(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
    Client client = new Client(username, encoder.encode(password), new HashSet<>());
    client.addNewRole(roleService.findRoleById(1L));
    clientService.saveNewClient(client);
    return "redirect:/login";
  }
}
