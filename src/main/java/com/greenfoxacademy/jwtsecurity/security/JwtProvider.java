package com.greenfoxacademy.jwtsecurity.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.greenfoxacademy.jwtsecurity.security.SecurityConstants.EXPIRATION_TIME;
import static com.greenfoxacademy.jwtsecurity.security.SecurityConstants.SECRET;
@Component
public class JwtProvider {

  public String generateJwtToken(String username, String password) {

    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }
}
