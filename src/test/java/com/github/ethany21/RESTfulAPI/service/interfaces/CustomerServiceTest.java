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

    private static Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);

    @BeforeEach
    void setUp(){
        customerService = new CustomerServiceImpl(customerRepository);

        Customer customer = new Customer();

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

        String firstName = "kanye";
        String lastName = "frank";
        String email = "frank@gmail.com";
        Gender gender = Gender.MALE;

        customerRepository.save(Customer.builder()
            .firstName(firstName)
            .lastName(lastName)
            .email(email)
            .gender(gender)
            .build()
        );

        logger.info("*** test FindById Method ***");

        List<Customer> foundCustomer = customerRepository.findAll();

        Customer customer = foundCustomer.get(0);

        assertThat(customer.getFirstName()).isEqualTo(firstName);
        logger.info("result is : " + foundCustomer.get(0).toString());

    }

    @Test
    void getAllCustomers(){

        customerService = new CustomerServiceImpl(customerRepository);

        Customer customer = new Customer();

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
    void checkDeleteCustomer(){

        Customer customer = new Customer();

        customer.setFirstName("kayne");
        customer.setLastName("frank");
        customer.setEmail("frank@gmail.com");
        customer.setGender(Gender.MALE);
        customerService.save(customer);
        //when
        customerService.save(customer);

        customerService.delete(customer);
        ArgumentCaptor<Customer>customerArgumentCaptor=
                ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).delete(customerArgumentCaptor.capture());
        assertThat(customerService.findAll().size()).isEqualTo(0);
    }

}