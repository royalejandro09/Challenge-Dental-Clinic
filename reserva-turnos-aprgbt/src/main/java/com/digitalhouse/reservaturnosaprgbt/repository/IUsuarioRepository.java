package com.digitalhouse.reservaturnosaprgbt.repository;

import com.digitalhouse.reservaturnosaprgbt.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends IRepository<Usuario, Integer> {


    /**
     * Metodos
     */
    Usuario findOneByUsername(String username);
}
