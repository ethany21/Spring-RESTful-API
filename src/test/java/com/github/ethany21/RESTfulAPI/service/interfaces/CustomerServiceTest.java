package com.github.ethany21.RESTfulAPI.service.interfaces;

import com.github.ethany21.RESTfulAPI.model.Customer;
import com.github.ethany21.RESTfulAPI.model.Gender;
import com.github.ethany21.RESTfulAPI.repository.CustomerRepository;
import com.github.ethany21.RESTfulAPI.service.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.rmi.CORBA.StubDelegate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @Autowired
    Customer customer;

    private static Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);

    @BeforeEach
    void setUp(){
        customerService = new CustomerServiceImpl(customerRepository);

        customer = new Customer();

        customer.setFirstName("kayne");
        customer.setLastName("frank");
        customer.setEmail("frank@gmail.com");
        customer.setGender(Gender.MALE);
        customerService.save(customer);
    }

    @AfterEach
    void clean(){
        customerRepository.deleteAll();
    }


    @Test
    @Transactional
    public void testFindByFirstName(){

        customerRepository.save(customer);

        logger.info("*** test FindById Method ***");

        List<Customer> foundCustomer = customerRepository.findByFirstName(customer.getFirstName());
        assertThat(foundCustomer).isNotEmpty();
        logger.info("result is : " + foundCustomer.get(0).toString());

    }

    @Test
    void getAllCustomers(){

        customerService = new CustomerServiceImpl(customerRepository);

        customer = new Customer();

        customer.setFirstName("kayne");
        customer.setLastName("frank");
        customer.setEmail("frank@gmail.com");
        customer.setGender(Gender.MALE);
        customerService.save(customer);

        //when
        customerService.findAll();

        //then
        verify(customerRepository).findAll();

        System.out.println(customerService.findAll().size());
    }

    @Test
    void addCustomer(){


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


        //when
        customerService.save(customer);

        customerService.delete(customer);
        ArgumentCaptor<Customer>customerArgumentCaptor=
                ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).delete(customerArgumentCaptor.capture());
        assertThat(customerService.findAll().size()).isEqualTo(0);
    }

}