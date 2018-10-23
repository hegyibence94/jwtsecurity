package com.greenfoxacademy.jwtsecurity.repository;

import com.greenfoxacademy.jwtsecurity.models.ClientRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<ClientRole, Long> {
  ClientRole findByName(String name);
}
