package com.github.ethany21.RESTfulAPI.repository;

import com.github.ethany21.RESTfulAPI.model.Customer;
import com.github.ethany21.RESTfulAPI.model.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@RunWith(SpringRunner.class)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    String firstName = "kanye";
    String lastName = "frank";
    String email = "frank@gmail.com";
    Gender gender = Gender.MALE;

    @BeforeEach
    void setUp(){

        repository.save(Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .gender(gender)
                .build()
        );
    }

    @AfterEach
    void clean(){
        repository.deleteAll();
    }




    @Test
    public void findCustomer(){

        Customer customer = Customer.builder()
                .firstName("John")
                .lastName("Lark")
                .email("jlark@gmail.com")
                .gender(Gender.MALE)
                .build();

        repository.save(customer);

        Customer test1 = repository.findByFirstName("John").get(1);
        Customer test2 = repository.findByFirstName("John").get(0);


        assertThat(test2.getLastName()).isEqualTo("Michael");
        assertThat(test1.getLastName()).isEqualTo("Lark");


    }

    @Test
    public void testFindByFirstName(){

        List<Customer> foundCustomer = repository.findByFirstName(firstName);

        Customer customer = foundCustomer.get(0);

        assertThat(customer.getFirstName()).isEqualTo(firstName);

    }

}