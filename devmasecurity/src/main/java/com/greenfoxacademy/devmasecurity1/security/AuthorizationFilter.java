package com.greenfoxacademy.devmasecurity1.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.greenfoxacademy.devmasecurity1.security.SecurityConstants.SECRET;

public class AuthorizationFilter extends BasicAuthenticationFilter {

  private final ClientDetailsService clientDetailsService;

  public AuthorizationFilter(AuthenticationManager authenticationManager, ClientDetailsService clientDetailsService) {
    super(authenticationManager);
    this.clientDetailsService = clientDetailsService;
  }


  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws IOException, ServletException {
    Cookie cookie = WebUtils.getCookie(request, "Authorization");
    if (cookie == null || !cookie.getValue().startsWith("Bearer ")) {
      chain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, "Authorization");
    String token = cookie.getValue();
    if (token == null) return null;
    String username = Jwts.parser().setSigningKey(SECRET)
        .parseClaimsJws(token.replace("Bearer ", ""))
        .getBody()
        .getSubject();
    UserDetails userDetails = clientDetailsService.loadUserByUsername(username);
    return username != null ?
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()) : null;
  }
}
