package com.greenfoxacademy.jwtsecurity.security;

import com.greenfoxacademy.jwtsecurity.models.Client;
import com.greenfoxacademy.jwtsecurity.repository.ClientRepository;
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
public class ClientDetailsService implements  UserDetailsService {

  @Autowired
  ClientRepository clientRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Client client = clientRepository.findByEmail(username).get();
    return createUserFromClient(client);
  }

  public User createUserFromClient(Client client) {
    List<GrantedAuthority> authorities = client.getRoles().stream().map(role ->
        new SimpleGrantedAuthority(role.getName().name())
    ).collect(Collectors.toList());

    return new User(
        client.getEmail(),
        client.getPassword(),
        authorities);
  }

}
