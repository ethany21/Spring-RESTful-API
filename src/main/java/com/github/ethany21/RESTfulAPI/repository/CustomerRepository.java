package com.github.ethany21.RESTfulAPI.repository;

import com.github.ethany21.RESTfulAPI.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
