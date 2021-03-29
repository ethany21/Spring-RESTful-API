package com.github.ethany21.RESTfulAPI.dto;

import com.github.ethany21.RESTfulAPI.model.Customer;
import com.github.ethany21.RESTfulAPI.model.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CustomerDto {

    private String firstName;

    private String lastName;

    private String email;

    private Gender gender;

    @Builder

    public CustomerDto(String firstName, String lastName, String email, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public Customer toEntity(){
        return Customer.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .gender(gender)
                .build();
    }
}
