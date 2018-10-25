package com.greenfoxacademy.devmasecurity1.controller;

import com.greenfoxacademy.devmasecurity1.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  AdminService adminService;

  @GetMapping("/edit-user-roles")
  public String editUserRoles(Model model) {
    model.addAttribute("clients", adminService.findAll());
    return "edit-user-roles";
  }
}
