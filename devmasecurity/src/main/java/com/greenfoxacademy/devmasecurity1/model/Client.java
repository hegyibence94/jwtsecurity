package com.greenfoxacademy.devmasecurity1.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "client_id"),
      inverseJoinColumns = @JoinColumn(name = "client_role_id"))
  private Set<ClientRole> roles;

  public Client() {
    roles = new HashSet<>();
  }

  public Client(String username, String password, Set<ClientRole> roles) {
    this.username = username;
    this.password = password;
    this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<ClientRole> getRoles() {
    return roles;
  }

  public void setRoles(Set<ClientRole> roles) {
    this.roles = roles;
  }

  public void addNewRole(ClientRole newRole) {
    roles.add(newRole);
  }
}