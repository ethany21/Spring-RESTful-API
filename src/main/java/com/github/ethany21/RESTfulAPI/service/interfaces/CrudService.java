package com.github.ethany21.RESTfulAPI.service.interfaces;

import java.util.List;

public interface CrudService<T, ID> {

    List<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T obeject);

    void deleteById(ID id);

}
