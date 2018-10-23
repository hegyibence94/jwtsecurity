package com.greenfoxacademy.jwtsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.greenfoxacademy.jwtsecurity.security.SecurityConstants.HEADER_STRING;
import static com.greenfoxacademy.jwtsecurity.security.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  AuthenticationManager authenticationManager;

  JwtProvider jwtProvider = new JwtProvider();

  protected JwtAuthenticationFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
    super(defaultFilterProcessesUrl);
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.getParameter("username"),
        request.getParameter("password")));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    String token = jwtProvider.generateJwtToken(authResult);
    Cookie jwtCookie = new Cookie(HEADER_STRING, TOKEN_PREFIX + token);
    jwtCookie.setPath("/");
    response.addCookie(jwtCookie);
    response.sendRedirect("/homepage");
  }
}
