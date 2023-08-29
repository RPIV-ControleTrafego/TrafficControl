package com.rp.trafego.controllers;

// imports - bibiliotecas
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// imports - classes do projeto
import com.rp.trafego.models.Admin;
import com.rp.trafego.repository.AdminRepo;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String index() {
        return "login/index";
    }
}
