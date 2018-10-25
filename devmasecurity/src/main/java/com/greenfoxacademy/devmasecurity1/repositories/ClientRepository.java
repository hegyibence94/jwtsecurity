package com.greenfoxacademy.devmasecurity1.repositories;

import com.greenfoxacademy.devmasecurity1.model.Client;
import com.greenfoxacademy.devmasecurity1.model.ClientRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
  Client findByUsername(String username);
  List<Client> findAllBy();
  Boolean existsByUsername(String username);

  //How to filter by name
/*
  List<Client> findAllByRolesNotContaining(ClientRole role);
  List<Client> findAllByRolesContaining(ClientRole role);
*/
}
