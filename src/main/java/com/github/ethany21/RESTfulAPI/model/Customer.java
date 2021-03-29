package com.github.ethany21.RESTfulAPI.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="customer")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Builder
    public Customer(String firstName, String lastName, String email, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }
}
