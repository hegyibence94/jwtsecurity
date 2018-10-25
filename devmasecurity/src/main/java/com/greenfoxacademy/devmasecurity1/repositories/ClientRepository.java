package com.greenfoxacademy.devmasecurity1.repositories;

import com.greenfoxacademy.devmasecurity1.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
  Client findByUsername(String username);
}
