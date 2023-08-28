package com.rp.trafego.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rp.trafego.repository.AdminRepo;
import com.rp.trafego.models.Admin;

@Controller
public class AdminController {

    @Autowired
    private AdminRepo repo;

    @GetMapping("/administradores")
    public String index(Model model) {
        List<Admin> admins = (List<Admin>)repo.findAll();
        model.addAttribute("admins", admins);
        return "administradores/index";
    }
}
