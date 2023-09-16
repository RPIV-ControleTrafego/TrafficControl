package com.rp.trafego.controllers;

// imports - bibliotecas
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador responsável por manipular as requisições relacionadas à página
 * inicial.
 */
@Controller
public class HomeController {

    /**
     * Exibe a página inicial.
     *
     * @param model O modelo que será usado para enviar dados à visão.
     * @return Uma string que representa o nome da visão da página inicial.
     */
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Boolean usuarioAutenticado = (Boolean) session.getAttribute("usuarioAutenticado");

        if (usuarioAutenticado != null && usuarioAutenticado) {
            return "home/index";
        } else {
            return "redirect:/login";
        }
    }
}
