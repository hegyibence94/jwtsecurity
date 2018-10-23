package com.greenfoxacademy.jwtsecurity.service;

import com.greenfoxacademy.jwtsecurity.models.Client;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ClientService extends UserDetailsService {
  User createUserFromClient(Client client);
  Client saveNewClient(Client client);
}
