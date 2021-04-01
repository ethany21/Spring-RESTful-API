package com.github.ethany21.RESTfulAPI.controller;

import com.github.ethany21.RESTfulAPI.exception.CustomException;
import com.github.ethany21.RESTfulAPI.model.Customer;
import com.github.ethany21.RESTfulAPI.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final CustomerService customerService;

    @GetMapping("/test/{customerId}")
    public Customer customer(@PathVariable long customerId){
        return customerService.findById(customerId).orElseThrow(() -> new CustomException("Customer Not found with id " + customerId));
    }

    @GetMapping("/test")
    public List<Customer> customers() {
        return customerService.findAll();
    }

    @PostMapping(value = "/save")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @PutMapping("/update/{customerId}")
    public Customer updateCustomer(@PathVariable(value = "customerId") long id, @RequestBody Customer responseCustomer) throws CustomException {

        Customer customer = customerService.findById(id)
                .orElseThrow(() -> new CustomException("Customer Not found with id " + id));

        customer.setLastName(responseCustomer.getLastName());
        customer.setFirstName(responseCustomer.getFirstName());
        customer.setEmail(responseCustomer.getEmail());

        return customerService.save(customer);
    }

    @DeleteMapping("/delete/{customerId}")
    public void deleteCustomer(@PathVariable(value = "customerId") long id){

        customerService.deleteById(id);

    }


}
