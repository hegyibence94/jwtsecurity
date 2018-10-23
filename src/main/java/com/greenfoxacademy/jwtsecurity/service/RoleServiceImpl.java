package com.greenfoxacademy.jwtsecurity.service;

import com.greenfoxacademy.jwtsecurity.models.ClientRole;
import com.greenfoxacademy.jwtsecurity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  RoleRepository repository;

  @Override
  public ClientRole findRoleById(Long id) {
    return repository.findById(id).get();
  }
}
