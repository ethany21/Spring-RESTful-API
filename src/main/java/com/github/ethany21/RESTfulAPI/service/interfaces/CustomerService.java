package com.github.ethany21.RESTfulAPI.service.interfaces;

import com.github.ethany21.RESTfulAPI.model.Customer;

import java.util.List;

public interface CustomerService extends CrudService<Customer, Long> {

    List<Customer> findByFirstName(String firstName);

}
