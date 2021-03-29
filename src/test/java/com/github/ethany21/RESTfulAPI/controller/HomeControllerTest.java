package com.github.ethany21.RESTfulAPI.controller;

import com.github.ethany21.RESTfulAPI.dto.CustomerDto;
import com.github.ethany21.RESTfulAPI.model.Customer;
import com.github.ethany21.RESTfulAPI.model.Gender;
import com.github.ethany21.RESTfulAPI.repository.CustomerRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomerRepository customerRepository;

    @After
    public void tearDown() throws Exception{
        customerRepository.deleteAll();
    }

    @Test
    void customer() throws Exception{

        String firstName = "woodam";
        String lastName = "chung";
        String email = "imwoodam@hanmail.net";
        Gender gender = Gender.MALE;

        CustomerDto customer = CustomerDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .gender(gender)
                .build();

        String url = "http://localhost:" + port + "/save";

        System.out.println(port);

        ResponseEntity<Object> responseEntity= restTemplate.postForEntity(url, customer, Object.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Customer> customers = customerRepository.findAll();

        assertThat(customers.get(3).getFirstName()).isEqualTo(firstName);
        assertThat(customers.get(3).getLastName()).isEqualTo(lastName);
        assertThat(customers.get(3).getGender()).isEqualTo(gender);
        assertThat(customers.get(3).getEmail()).isEqualTo(email);

    }

    @Test
    void customers() {
    }

    @Test
    void createCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() {
    }
}