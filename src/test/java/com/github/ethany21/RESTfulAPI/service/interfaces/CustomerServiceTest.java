package com.github.ethany21.RESTfulAPI.service.interfaces;

import com.github.ethany21.RESTfulAPI.repository.CustomerRepository;
import com.github.ethany21.RESTfulAPI.service.CustomerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    private AutoCloseable autoCloseable;
    private CustomerService customerService;

    @BeforeEach
    void setUp(){

        autoCloseable = MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllCustomers(){

        //when
        customerService.findAll();

        //then
        verify(customerRepository).findAll();
    }

}