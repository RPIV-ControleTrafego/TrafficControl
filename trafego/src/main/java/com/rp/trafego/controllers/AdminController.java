package com.rp.trafego.controllers;

// imports - bibliotecas
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// imports - classes do projeto
import com.rp.trafego.repository.AdminRepo;
import com.rp.trafego.models.Admin;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos
 * administradores.
 */
@Controller
public class AdminController {

    /**
     * Repositório de admins injetado para acesso a dados do BD..
     */
    @Autowired
    private AdminRepo repository;

    /**
     * Retorna a página de índice para os administradores.
     *
     * @param model O modelo que será usado para enviar dados à visão.
     * @return Uma string que representa o nome da visão a ser renderizada.
     */
    @GetMapping("/administradores")
    public String index(Model model) {
        List<Admin> admins = (List<Admin>) repository.findAll();
        model.addAttribute("admins", admins);
        return "administradores/index";
    }

    /**
     * Retorna a página para adicionar um novo administrador.
     *
     * @return Uma string que representa o nome da visão para a página de adição de novo administrador.
     */
    @GetMapping("/administradores/newAdmin")
    public String novo() {
        return "administradores/newAdmin";
    }

    /**
     * Cria um novo administrador.
     *
     * Ele recebe um objeto do tipo Admin como parâmetro, o salva no repositório e
     * redireciona para a página de índice de administradores.
     *
     * @param admin O objeto Admin a ser criado e salvo.
     * @return Uma string que representa o redirecionamento para a página de índice de administradores.
     */
    @PostMapping("/administradores/criar")
    public String criar(Admin admin) {
        repository.save(admin);
        return "redirect:/administradores";
    }

    /**
     * Exclui um administrador pelo ID.
     *
     * Ele recebe um objeto do tipo Admin como parâmetro, remove o mesmo do
     * repositório e redireciona para a página de índice de administradores.
     *
     * @param id O ID do administrador a ser excluído.
     * @return Uma string que representa o redirecionamento para a página de índice de administradores.
     */
    @GetMapping("/administradores/{id}/excluir")
    public String excluir(@PathVariable int id) {
        repository.deleteById(id);
        return "redirect:/administradores";
    }

    /**
     * Busca um administrador pelo ID e exibe a página de edição.
     * 
     * O método busca o administrador no repositório usando o ID fornecido,
     * adiciona-o ao modelo e redireciona para a página de edição "administradores/editAdmin".
     *
     * @param id    O ID do administrador a ser buscado.
     * @param model O modelo que será usado para enviar dados à visão.
     * @return Uma string que representa o nome da visão para a página de edição de administrador.
     */
    @GetMapping("/administradores/{id}")
    public String busca(@PathVariable int id, Model model) {
        Optional<Admin> admin = repository.findById(id);

        try {
            model.addAttribute("admin", admin.get());
        } catch (Exception err) {
            return "redirect:/administradores";
        }

        return "/administradores/editAdmin";
    }

    /**
     * Atualiza as informações de um administrador pelo ID.
     *
     * O método verifica se o administrador com o ID fornecido existe no
     * repositório. Se existir, as informações do administrador são atualizadas no repositório.
     *
     * @param id    O ID do administrador a ser atualizado.
     * @param admin O objeto Admin com as informações atualizadas.
     * @return Uma string que representa o redirecionamento para a página de índice de administradores.
     */
    @PostMapping("/admins/{id}/update")
    public String update(@PathVariable int id, Admin admin) {
        if (!repository.existsById(id)) {
            return "redirect:/administradores";
        }

        repository.save(admin);
        return "redirect:/administradores";
    }
}
