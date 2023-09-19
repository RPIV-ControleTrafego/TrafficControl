package com.rp.trafego.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcidentesController {
    
    @GetMapping("/acidentes")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Boolean usuarioAutenticado = (Boolean) session.getAttribute("usuarioAutenticado");
    
        if(usuarioAutenticado != null && usuarioAutenticado) {
            return "acidentes/acidentes";
        } else {
            return "redirect:/login";
        }
    }

}
