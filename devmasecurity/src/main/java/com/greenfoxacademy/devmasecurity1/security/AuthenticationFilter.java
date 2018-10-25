package com.greenfoxacademy.devmasecurity1.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.greenfoxacademy.devmasecurity1.security.SecurityConstants.TOKEN_KEY;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;
  private JwtProvider jwtProvider;

  public AuthenticationFilter(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
    this.authenticationManager = authenticationManager;
    this.jwtProvider = jwtProvider;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.getParameter("username"),
        request.getParameter("password")));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    String token = jwtProvider.generateJwtToken(authResult);
    Cookie cookie = new Cookie(TOKEN_KEY, token);
    cookie.setPath("/");
    response.addCookie(cookie);
    response.sendRedirect("/home");
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    response.sendRedirect("/login");
  }
}
