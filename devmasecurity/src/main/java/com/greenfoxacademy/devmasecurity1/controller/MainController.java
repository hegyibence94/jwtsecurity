package com.greenfoxacademy.devmasecurity1.controller;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
  @Bean
  public WebServerFactoryCustomizer customizer() {
    return container -> {
      if (container instanceof TomcatServletWebServerFactory) {
        TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) container;
        tomcat.addContextCustomizers(context -> context.setCookieProcessor(new LegacyCookieProcessor()));
      }
    };
  }


  @GetMapping("/login")
  public String getLoginPage() {
    return "login";
  }

  @PostMapping("/login")
  public String submitLoginPage() {
    return "redirect:/nyeh";
  }

  @GetMapping("/home")
  public String getHomePage() {
    return "home";
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
