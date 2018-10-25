package com.greenfoxacademy.devmasecurity1.services;

import com.greenfoxacademy.devmasecurity1.model.Client;
import com.greenfoxacademy.devmasecurity1.model.ClientRole;

import java.util.List;

public interface AdminService {
  List<Client> findAllClients();
  List<ClientRole> findAllRoles();
  void addRoleToClient(Long clientId, Long roleId);
  void deleteRoleFromClient(Long clientId, Long roleId);

  //How to
  /*List<Client> findAllNotAdminClients();
  List<Client> findAllAdminClients();*/

}
