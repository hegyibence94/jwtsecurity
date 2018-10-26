package com.greenfoxacademy.devmasecurity1.controller;

import com.greenfoxacademy.devmasecurity1.model.Client;
import com.greenfoxacademy.devmasecurity1.security.modeldto.ClientDTO;
import com.greenfoxacademy.devmasecurity1.services.ClientService;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.greenfoxacademy.devmasecurity1.security.WebSecurityConfig.passwordEncoder;

@Controller
public class MainController {

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

  @GetMapping("/register")
  public String getRegisterPage() {
    return "register";
  }

  @PostMapping("/register")
  public String submitRegister(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password) {
    if (!clientService.checkIfExistsByUsername(username)) {
      clientService.createNewClient(new Client(username, passwordEncoder().encode(password)));
    }
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String getLoginPage() {
    return "login";
  }

  @PostMapping("/login")
  public void submitLoginPage() {
    return;
  }

  @GetMapping("")
  public String getHomePage() {
    return "index";
  }
}
