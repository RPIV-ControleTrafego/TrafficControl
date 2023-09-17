package com.rp.trafego.controllers;

import java.io.IOException;

// imports - bibiliotecas
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// imports - classes do projeto
import com.rp.trafego.models.Admin;
import com.rp.trafego.repository.AdminRepo;
import com.rp.trafego.service.CookieService;

/**
 * Controlador responsável por manipular as requisições relacionadas ao login.
 */
@Controller
public class LoginController {

    @Autowired
    private AdminRepo repository;

    /**
     * Mapeia a rota "/login" para exibir a página de login.
     *
     * @return Uma string que representa o nome da visão da página de login.
     */
    @GetMapping("/login")
    public String index() {
        return "login/index";
    }

    /**
     * Mapeia a ação de login ("/logar") após o envio do formulário de login.
     * Verifica as credenciais do administrador no banco de dados e cria uma sessão de usuário autenticado se as credenciais forem válidas.
     *
     * @param model          O modelo que será usado para enviar dados à visão.
     * @param adminParameter O objeto Admin contendo as credenciais fornecidas no formulário.
     * @param remember       Uma string que pode ser usada para lembrar informações adicionais de login (opcional).
     * @param request        O HttpServletRequest usado para acessar a sessão.
     * @return Uma string que representa o redirecionamento para a página inicial se o login for bem-sucedido,
     *         ou uma mensagem de erro se as credenciais forem inválidas.
     */
    @PostMapping("/logar")
    public String logar(Model model, Admin adminParameter, String remember, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Admin admin = this.repository.loginCheck(adminParameter.getEmail(), adminParameter.getSenha());

        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioAutenticado", true);

            CookieService.setCookie(response, "usuarioId", String.valueOf(admin.getId()), 5);

            return "redirect:/";
        }

        model.addAttribute("erro", "Usuário e/ou senha incorretos!");
        return "login/index";
    }

    /**
     * Mapeia a ação de logout ("/logout").
     * Remove a sessão de usuário autenticado, efetuando o logout.
     *
     * @param request O HttpServletRequest usado para acessar a sessão.
     * @return Uma string que representa o redirecionamento para a página de login após o logout.
     */
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {   
        HttpSession session = request.getSession();
        session.removeAttribute("usuarioAutenticado");  
        return "redirect:/login";
    }
}
