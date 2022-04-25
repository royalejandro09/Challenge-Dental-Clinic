package com.digitalhouse.reservaturnosaprgbt.repository.service;

import java.util.List;

public interface ICrud<T, K> {

    /**
     * Metodos del CRUD
     */
    T save(T t) throws Exception;

    //T update(T t) throws Exception;

    List<T> findAll() throws Exception;

    T findById(K id) throws Exception;

    void delete(K id) throws Exception;
}
