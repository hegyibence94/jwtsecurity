package com.greenfoxacademy.devmasecurity1.security;

import com.greenfoxacademy.devmasecurity1.model.Client;
import com.greenfoxacademy.devmasecurity1.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClientDetailsService implements UserDetailsService {
  @Autowired
  ClientService clientService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Client client = clientService.findByUsername(username);
    return createUserFromClient(client);
  }

  public User createUserFromClient(Client client) {
    /*if (username.equals("Ben")) {
      return new User("Ben","{noop}pass", AuthorityUtils.createAuthorityList());
    } else if (username.equals("User")) {
      return new User("User","{noop}pass", AuthorityUtils.createAuthorityList("ROLE_USER"));
    } else {
      return new User("Admin","{noop}pass", AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
    }*/

    List<GrantedAuthority> authorities = client.getRoles().stream().map(role ->
        new SimpleGrantedAuthority(role.getName().name())
    ).collect(Collectors.toList());

    return new User(
        client.getUsername(),
        client.getPassword(),
        authorities);
  }
}

