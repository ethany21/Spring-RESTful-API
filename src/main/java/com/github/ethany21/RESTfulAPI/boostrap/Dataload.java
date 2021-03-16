package com.github.ethany21.RESTfulAPI.boostrap;

import com.github.ethany21.RESTfulAPI.model.Customer;
import com.github.ethany21.RESTfulAPI.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class Dataload implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(Dataload.class);
    private final CustomerService customerService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        dataLoad();
    }

    public void dataLoad(){

        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Michael");
        customer1.setEmail("jmichael@gmail.com");

        Customer customer2 = new Customer();
        customer2.setFirstName("Linda");
        customer2.setLastName("Hal");
        customer2.setEmail("hallinda@gmail.com");

        Customer customer3 = new Customer();
        customer3.setFirstName("Bill");
        customer3.setLastName("Patrick");
        customer3.setEmail("buchanyon@gmail.com");

        customerService.save(customer1);
        customerService.save(customer2);
        customerService.save(customer3);

    }
}
