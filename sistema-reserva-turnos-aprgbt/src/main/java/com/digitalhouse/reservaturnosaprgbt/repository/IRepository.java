package com.digitalhouse.reservaturnosaprgbt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IRepository<T, ID> extends JpaRepository<T, ID> {

    /**
     * Extiendo de JPA Repository para Obtener los metodos CRUD.
     */
}
