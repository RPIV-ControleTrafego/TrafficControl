package com.rp.trafego.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/administradores/newAdmin")
    public String novo() {
        return "administradores/newAdmin";
    }

    @PostMapping("/administradores/criar")
    public String criar(Admin admin) {
        repo.save(admin);
        return "redirect:/administradores";
    }

    @GetMapping("/administradores/{id}/excluir")
    public String excluir(@PathVariable int id) {
        repo.deleteById(id);
        return "redirect:/administradores";
    }

    @GetMapping("/administradores/{id}")
    public String busca(@PathVariable int id, Model model) {
        Optional<Admin> admin = repo.findById(id);
        
        try{
            model.addAttribute("admin", admin.get());
        }    catch(Exception err){ return "redirect:/administradores"; }

        return "/administradores/editAdmin";
    }

    @PostMapping("/admins/{id}/update")
    public String update(@PathVariable int id, Admin admin) {
        if(!repo.existsById(id)) {
            return "redirect:/administradores";
        }

        repo.save(admin);

        return "redirect:/administradores";
    }
}
