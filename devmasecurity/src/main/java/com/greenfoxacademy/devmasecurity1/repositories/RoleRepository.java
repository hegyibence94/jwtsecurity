package com.greenfoxacademy.devmasecurity1.repositories;

import com.greenfoxacademy.devmasecurity1.model.ClientRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<ClientRole, Long> {
  ClientRole findByName(String name);
  List<ClientRole> findAllBy();
}