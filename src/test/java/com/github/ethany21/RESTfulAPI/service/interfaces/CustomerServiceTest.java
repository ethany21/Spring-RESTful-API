package com.github.ethany21.RESTfulAPI.service.interfaces;

import com.github.ethany21.RESTfulAPI.model.Customer;
import com.github.ethany21.RESTfulAPI.model.Gender;
import com.github.ethany21.RESTfulAPI.repository.CustomerRepository;
import com.github.ethany21.RESTfulAPI.service.CustomerServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerMockRepository;

    @Autowired
    private CustomerService customerService;

    private static Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);

    String firstName = "kanye";
    String lastName = "frank";
    String email = "frank@gmail.com";
    Gender gender = Gender.MALE;
    Customer customer;

    @BeforeEach
    void setUp(){
        customerService = new CustomerServiceImpl(customerMockRepository);

        customer = customerMockRepository.save(Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .gender(gender)
                .build()
        );
    }

    @AfterEach
    void clean(){
        customerMockRepository.deleteAll();
    }

    @Test
    void getAllCustomers(){

        //when
        customerService.findAll();

        //then
        verify(customerMockRepository).findAll();

        System.out.println(customerService.findAll().size());
    }

    @Test
    void checkDeleteCustomer(){

        customerService.delete(customer);
        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerMockRepository).delete(customerArgumentCaptor.capture());
        assertThat(customerService.findAll().size()).isEqualTo(0);
    }

}