package com.greenfoxacademy.devmasecurity1.services;

import com.greenfoxacademy.devmasecurity1.model.ClientRole;
import com.greenfoxacademy.devmasecurity1.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  RoleRepository roleRepository;

  @Override
  public ClientRole findClientRoleByName(String name) {
    return roleRepository.findByName(name);
  }
}
