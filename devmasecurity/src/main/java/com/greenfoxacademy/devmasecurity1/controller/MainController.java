package com.greenfoxacademy.devmasecurity1.controller;

import com.greenfoxacademy.devmasecurity1.model.Client;
import com.greenfoxacademy.devmasecurity1.security.modeldto.ClientDTO;
import com.greenfoxacademy.devmasecurity1.services.ClientService;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
  public String getRegisterPage(Model model) {
    model.addAttribute("newClient", new ClientDTO());
    return "register";
  }

  @PostMapping("/register")
  public String submitRegister(@ModelAttribute ClientDTO newClient) {
    if (!clientService.checkIfExistsByUsername(newClient.getUsername())) {
      clientService.createNewClient(new Client(newClient.getUsername(), passwordEncoder().encode(newClient.getPassword())));
    }
    return "redirect:/login";
  }

  @GetMapping("/login")
  public String getLoginPage(Model model) {
    model.addAttribute("client", new Client());
    return "login";
  }

  @PostMapping("/login")
  public void submitLoginPage() {
    return;
  }

  @GetMapping("/")
  public String getHomePage() {
    return "index";
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/user")
  public String getUserPage() {
    return "user";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/admin")
  public String getAdminPage() {
    return "admin";
  }

}
