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
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Mock
    private CustomerRepository customerMockRepository;
    private CustomerService customerService;

    private static Logger logger = LoggerFactory.getLogger(CustomerServiceTest.class);

    String firstName = "kanye";
    String lastName = "frank";
    String email = "frank@gmail.com";
    Gender gender = Gender.MALE;

    @BeforeEach
    void setUp(){
        customerService = new CustomerServiceImpl(customerMockRepository);

        customerMockRepository.save(Customer.builder()
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
    public void testFindByFirstName(){

        logger.info("*** test FindById Method ***");

        List<Customer> foundCustomer = customerRepository.findByFirstName(firstName);

        Customer customer = foundCustomer.get(0);

        assertThat(customer.getFirstName()).isEqualTo(firstName);
        logger.info("result is : " + foundCustomer.get(0).getLastName());

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