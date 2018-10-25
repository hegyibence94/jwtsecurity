package com.greenfoxacademy.devmasecurity1.services;

import com.greenfoxacademy.devmasecurity1.model.Client;

public interface ClientService {
  Client createNewClient(Client client);
  Client findByUsername(String username);
}
