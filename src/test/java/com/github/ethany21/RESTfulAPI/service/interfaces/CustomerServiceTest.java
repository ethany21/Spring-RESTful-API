package com.github.ethany21.RESTfulAPI.service.interfaces;

import com.github.ethany21.RESTfulAPI.model.Customer;
import com.github.ethany21.RESTfulAPI.model.Gender;
import com.github.ethany21.RESTfulAPI.repository.CustomerRepository;
import com.github.ethany21.RESTfulAPI.service.CustomerServiceImpl;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.rmi.CORBA.StubDelegate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp(){
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @AfterEach
    void clean(){
        customerRepository.deleteAll();
    }

    @Test
    void getAllCustomers(){

        //when
        customerService.findAll();

        //then
        verify(customerRepository).findAll();
    }

    @Test
    void addCustomer(){


        //setup
        Customer customer = new Customer();
        customer.builder()
                .firstName("kayne")
                .lastName("frank")
                .email("frank@gmail.com")
                .gender(Gender.MALE)
                .build();


        //when
        customerService.save(customer);

        //then
        ArgumentCaptor<Customer>customerArgumentCaptor=
                ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(customer);

    }

    @Test
    void checkDeleteCustomer(){
        Customer customer = new Customer();
        customer.builder()
                .firstName("kayne")
                .lastName("frank")
                .email("frank@gmail.com")
                .gender(Gender.MALE)
                .build();


        //when
        customerService.save(customer);

        System.out.println(customerService.save(customer));

        customerService.delete(customer);
        ArgumentCaptor<Customer>customerArgumentCaptor=
                ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).delete(customerArgumentCaptor.capture());
        System.out.println(customerService.findAll());
        assertThat(customerService.findAll().size()).isEqualTo(0);
    }

}