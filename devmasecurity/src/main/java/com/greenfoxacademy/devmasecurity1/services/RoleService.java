package com.greenfoxacademy.devmasecurity1.services;

import com.greenfoxacademy.devmasecurity1.model.ClientRole;

public interface RoleService {

  ClientRole findClientRoleByName(String name);
}
