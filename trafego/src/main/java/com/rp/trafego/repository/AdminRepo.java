package com.rp.trafego.repository;

// import - classe do projeto
import com.rp.trafego.models.Admin;

// imports - bibliotecas
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Interface de repositório para entidade Admin.
 *
 * Esta interface define operações de acesso a dados relacionadas aos administradores.
 */
public interface AdminRepo extends CrudRepository<Admin, Integer> {

    /**
    * Verifica se um administrador com o ID fornecido existe no repositório.
    *
    * @param id O ID do administrador a ser verificado.
    * @return true se o administrador existe, false se não existir.
    */
    @Query(value="select CASE WHEN count(1) > 0 THEN 'true' ELSE 'false' END  from administradores where id = :id", nativeQuery = true)
    public boolean exist(int id);
}
