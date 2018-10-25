package com.greenfoxacademy.devmasecurity1.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;

  public AuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    System.out.println(request.getParameter("username") + request.getParameter("password"));
    try {
     return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          request.getParameter("username"),
          request.getParameter("password")));
    } catch (Exception e) {
      System.out.println("Nem siekrült :(");
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    String username = ((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername();
    String token = Jwts
        .builder()
        .setSubject(username)
        .setExpiration(new Date(System.currentTimeMillis() + 60000000000L))
        .signWith(SignatureAlgorithm.HS512, "nagy secret")
        .compact();
    String bearerToken = "Bearer " + token;
    Cookie cookie = new Cookie("Authorization", bearerToken);
    cookie.setPath("/");
    response.addCookie(cookie);
    response.sendRedirect("/home");
  }
}
