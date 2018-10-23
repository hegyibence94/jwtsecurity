package com.greenfoxacademy.jwtsecurity.service;

import com.greenfoxacademy.jwtsecurity.models.ClientRole;

public interface RoleService {
  ClientRole findRoleById(Long id);
}
