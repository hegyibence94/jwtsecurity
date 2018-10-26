package com.greenfoxacademy.devmasecurity1.security;

import com.greenfoxacademy.devmasecurity1.model.Client;
import com.greenfoxacademy.devmasecurity1.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientDetailsService implements UserDetailsService {
  @Autowired
  ClientService clientService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Client client = clientService.findByUsername(username);
    return createSecurityUserFromClient(client);
  }

  public User createSecurityUserFromClient(Client client) {
    List<GrantedAuthority> authorities = client.getRoles().stream().map(role ->
        new SimpleGrantedAuthority(role.getName().name())
    ).collect(Collectors.toList());
    return new User(
        client.getUsername(),
        client.getPassword(),
        authorities);
  }
}

