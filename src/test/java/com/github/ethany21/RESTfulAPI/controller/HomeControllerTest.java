package com.github.ethany21.RESTfulAPI.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ethany21.RESTfulAPI.dto.CustomerDto;
import com.github.ethany21.RESTfulAPI.model.Customer;
import com.github.ethany21.RESTfulAPI.model.Gender;
import com.github.ethany21.RESTfulAPI.repository.CustomerRepository;
import com.github.ethany21.RESTfulAPI.service.interfaces.CustomerService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;

import javax.print.attribute.standard.Media;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        HomeController controller = new HomeController(customerService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

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
    void createCustomer() throws Exception{

        String firstName = "Daniel";
        String lastName = "Windsor";
        String email = "daniel@gmail.com";
        Gender gender = Gender.MALE;

        CustomerDto customer = CustomerDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .gender(gender)
                .build();

        String content = objectMapper.writeValueAsString(customer);

        mockMvc.perform(post("/save")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomer() throws Exception{

        System.out.println(customerRepository.findById(0l));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/delete/{customerId}", 0l)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}