package com.greenfoxacademy.jwtsecurity.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static com.greenfoxacademy.jwtsecurity.security.SecurityConstants.EXPIRATION_TIME;
import static com.greenfoxacademy.jwtsecurity.security.SecurityConstants.SECRET;

@Component
public class JwtProvider {

  protected static String generateJwtToken(Authentication authentication) {
    return Jwts.builder()
        .setSubject(((User) authentication.getPrincipal()).getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  protected String getJwtStringFromHeader(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, "Authorization");
    String authHeader = cookie.getValue();
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      return authHeader.replace("Bearer ", "");
    }
    return null;
  }

  protected String getUserNameFromJwtToken(String token) {
    return Jwts.parser()
        .setSigningKey(SECRET)
        .parseClaimsJws(token)
        .getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
    } catch (MalformedJwtException e) {
    } catch (ExpiredJwtException e) {
    } catch (UnsupportedJwtException e) {
    } catch (IllegalArgumentException e) {
    }
    return false;
  }

}
