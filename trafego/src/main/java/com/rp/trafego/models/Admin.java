package com.rp.trafego.models;

// imports - bibliotecas
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade (objeto) que representa um administrador.
 *
 * Esta classe é mapeada para a tabela "admins" no banco de dados.
 */
@Entity
@Table(name = "admins")
public class Admin {

    /**
     * O ID único do administrador.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * O nome do administrador.
     */
    @Column(name = "nome", nullable = false)
    private String nome;

    /**
     * O email do administrador.
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * A senha do administrador.
     */
    @Column(name = "senha", nullable = false)
    private String senha;

    /**
     * Obtém o ID do administrador.
     *
     * @return O ID do administrador.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do administrador.
     *
     * @param id O ID do administrador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Recupera/retorna o nome do administrador.
     *
     * @return O nome do administrador.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do administrador.
     *
     * @param nome O nome do administrador.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Recupera/retorna o e-mail do administrador.
     *
     * @return O e-mail do administrador.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do administrador.
     *
     * @param email O e-mail do administrador.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Recupera/retorna a senha do administrador.
     *
     * @return A senha do administrador.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do administrador.
     *
     * @param senha A senha do administrador.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
