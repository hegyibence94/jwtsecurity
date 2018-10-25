package com.greenfoxacademy.devmasecurity1.services;

import com.greenfoxacademy.devmasecurity1.model.Client;
import com.greenfoxacademy.devmasecurity1.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
  @Autowired
  ClientRepository clientRepository;

  @Override
  public Client createNewClient(Client client) {
    return clientRepository.save(client);
  }

  @Override
  public Client findByUsername(String username) {
    return clientRepository.findByUsername(username);
  }
}
