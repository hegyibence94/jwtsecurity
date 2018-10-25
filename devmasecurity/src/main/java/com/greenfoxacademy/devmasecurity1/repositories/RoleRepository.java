package com.greenfoxacademy.devmasecurity1.repositories;

import com.greenfoxacademy.devmasecurity1.model.ClientRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<ClientRole, Long> {
  ClientRole findByName(String name);
}