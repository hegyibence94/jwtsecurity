package com.greenfoxacademy.devmasecurity1.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class ClientRole {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private RoleName name;

  public ClientRole() {
  }

  public ClientRole(RoleName name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public RoleName getName() {
    return name;
  }

  public void setName(RoleName name) {
    this.name = name;
  }
}
