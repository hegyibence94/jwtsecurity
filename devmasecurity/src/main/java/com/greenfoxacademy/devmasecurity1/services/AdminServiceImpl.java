package com.greenfoxacademy.devmasecurity1.services;

import com.greenfoxacademy.devmasecurity1.model.Client;
import com.greenfoxacademy.devmasecurity1.model.ClientRole;
import com.greenfoxacademy.devmasecurity1.model.RoleName;
import com.greenfoxacademy.devmasecurity1.repositories.ClientRepository;
import com.greenfoxacademy.devmasecurity1.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  ClientRepository clientRepository;

  @Autowired
  RoleRepository roleRepository;

  @Override
  public List<Client> findAllClients() {
    return clientRepository.findAllBy();
  }

  @Override
  public List<ClientRole> findAllRoles() {
    return roleRepository.findAllBy();
  }

  @Override
  public void addRoleToClient(Long clientId, Long roleId) {
    Client client = clientRepository.findById(clientId).get();
    client.addNewRole(roleRepository.findById(roleId).get());
    clientRepository.save(client);
  }

  @Override
  public void deleteRoleFromClient(Long clientId, Long roleId) {
    Client client = clientRepository.findById(clientId).get();
    client.deleteRole(roleRepository.findById(roleId).get());
    clientRepository.save(client);
  }


  //How to filter by Role
/*
  @Override
  public List<Client> findAllNotAdminClients() {
    return clientRepository.findAllByRolesNotContaining(roleRepository.findByName(RoleName.valueOf("ROLE_ADMIN")));
  }
  @Override
  public List<Client> findAllAdminClients() {
    return clientRepository.findAllByRolesContaining(roleRepository.findByName(RoleName.valueOf("ROLE_ADMIN")));
  }
*/
}
