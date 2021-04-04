package com.github.ethany21.RESTfulAPI.repository;

import com.github.ethany21.RESTfulAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFirstName(String firstName);

}
