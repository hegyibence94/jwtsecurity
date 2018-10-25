package com.greenfoxacademy.devmasecurity1.security.models;

public class ClientDTO {
  private String username;
  private String password;

  public ClientDTO() {
  }

  public ClientDTO(String username, String password) {
    this.username = username;
    this.password = password;
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
}
