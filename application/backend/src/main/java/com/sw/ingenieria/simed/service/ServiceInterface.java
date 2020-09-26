package com.sw.ingenieria.simed.service;

import java.util.List;

public interface ServiceInterface <E, PK>  {

    E findById(PK key) throws Exception;

    List<E> findAll() throws Exception;

    E create(E entity) throws Exception;
    E update(E entity, PK key) throws Exception;

    void delete(PK key) throws Exception;



}
