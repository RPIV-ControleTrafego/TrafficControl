package com.rp.trafego.controllers;

// imports - bibiliotecas
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// imports - classes do projeto
import com.rp.trafego.models.Admin;
import com.rp.trafego.repository.AdminRepo;

@Controller
public class LoginController {

    @Autowired
    private AdminRepo repository;

    @GetMapping("/login")
    public String index() {
        return "login/index";
    }

    @PostMapping("/logar")
    public String logar(Model model, Admin adminParameter, String remember, HttpServletRequest request) {
        Admin admin = this.repository.loginCheck(adminParameter.getEmail(), adminParameter.getSenha());

        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioAutenticado", true);

            return "redirect:/";
        }

        model.addAttribute("erro", "Usuário e/ou senha incorretos!");
        return "login/index";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {   
        HttpSession session = request.getSession();
        session.removeAttribute("usuarioAutenticado");  
        return "redirect:/login";
    }
}
