package com.github.ethany21.RESTfulAPI.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T object);

    void delete(T obeject);

    void deleteById(ID id);

}
