package com.greenfoxacademy.devmasecurity1.services;

import com.greenfoxacademy.devmasecurity1.model.Client;

import java.util.List;

public interface AdminService {
  List<Client> findAll();
}
