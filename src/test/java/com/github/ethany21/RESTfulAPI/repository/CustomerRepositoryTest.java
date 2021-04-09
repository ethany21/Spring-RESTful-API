package com.github.ethany21.RESTfulAPI.repository;

import com.github.ethany21.RESTfulAPI.model.Customer;
import com.github.ethany21.RESTfulAPI.model.Gender;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

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

}