package com.greenfoxacademy.jwtsecurity.controller;

import com.greenfoxacademy.jwtsecurity.security.JwtProvider;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.greenfoxacademy.jwtsecurity.security.SecurityConstants.HEADER_STRING;
import static com.greenfoxacademy.jwtsecurity.security.SecurityConstants.TOKEN_PREFIX;

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

  @Autowired
  JwtProvider jwtProvider;

  @GetMapping("/login")
  public String getLoginPage() {
    return "login";
  }

  @PostMapping("/login")
  public String submitLoginPage(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpServletResponse response) {
    String token = jwtProvider.generateJwtToken(username, password);
    Cookie jwtCookie = new Cookie(HEADER_STRING, TOKEN_PREFIX + token);
    jwtCookie.setPath("/");
    response.addCookie(jwtCookie);
    return "redirect:/homepage";
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
}
