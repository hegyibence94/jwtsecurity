package com.greenfoxacademy.devmasecurity1.repositories;

import com.greenfoxacademy.devmasecurity1.model.ClientRole;
import com.greenfoxacademy.devmasecurity1.model.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository extends CrudRepository<ClientRole, Long> {
  List<ClientRole> findAllBy();

  //How to filter by name
/*
  ClientRole findByName(RoleName name);
*/
}