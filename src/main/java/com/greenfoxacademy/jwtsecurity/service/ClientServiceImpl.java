package com.greenfoxacademy.jwtsecurity.service;

import com.greenfoxacademy.jwtsecurity.models.Client;
import com.greenfoxacademy.jwtsecurity.repository.ClientRepository;
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
}
