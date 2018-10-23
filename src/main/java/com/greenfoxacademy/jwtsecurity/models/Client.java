package com.greenfoxacademy.jwtsecurity.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String password;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "client_id"),
      inverseJoinColumns = @JoinColumn(name = "client_role_id"))
  private Set<ClientRole> roles;

  public Client() {
    roles = new HashSet<>();
  }

  public Client(String email, String password, Set<ClientRole> roles) {
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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
