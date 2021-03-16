package com.github.ethany21.RESTfulAPI.controller;

import com.github.ethany21.RESTfulAPI.model.Customer;
import com.github.ethany21.RESTfulAPI.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final CustomerService customerService;

    @GetMapping("/test/{studentId}")
    public Customer strings(@PathVariable long studentId){
        return customerService.findById(studentId);
    }

    @GetMapping("/test")
    public List<Customer> strings() {
        return customerService.findAll();
    }

    @PostMapping("/save")
    public Customer postStrings(@RequestBody Customer customer){
        return customerService.save(customer);
    }
}
