package com.greenfoxacademy.jwtsecurity.security;

import com.greenfoxacademy.jwtsecurity.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthorizationFilter {
  @Autowired
  JwtProvider jwtProvider;
  @Autowired
  ClientService clientService;


  public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    try {

      String jwt = jwtProvider.getJwtStringFromHeader(request);
      if (jwt != null && jwtProvider.validateJwtToken(jwt)) {
        String username = jwtProvider.getUserNameFromJwtToken(jwt);
        System.out.println(username);
        UserDetails userDetails = clientService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication
            = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      logger.error("Can NOT set user authentication -> Message: {}", e);
    }
    chain.doFilter(request, response);
  }
}
