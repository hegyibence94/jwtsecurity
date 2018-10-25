package com.greenfoxacademy.devmasecurity1.controller;

import com.greenfoxacademy.devmasecurity1.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  AdminService adminService;

  @GetMapping("")
  public String getAdminPage() {
    return "admin";
  }

  @GetMapping("/edit-client-roles")
  public String editClientRoles(Model model) {
    model.addAttribute("clients", adminService.findAllClients());
    model.addAttribute("roles", adminService.findAllRoles());
    return "edit-user-roles";
  }

  @PostMapping("/add-role")
  public String addRoleToClient(@RequestParam(value = "clientId") Long clientId, @RequestParam(value = "roleId") Long roleId) {
    adminService.addRoleToClient(clientId, roleId);
    return "redirect:/admin/edit-client-roles";
  }

  @PostMapping("/delete-role")
  public String deleteRoleFromClient(@RequestParam(value = "clientId") Long clientId, @RequestParam(value = "roleId") Long roleId) {
    adminService.deleteRoleFromClient(clientId, roleId);
    return "redirect:/admin/edit-client-roles";
  }
}
