package com.example.demo.domain;

import com.example.demo.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
public class Driver extends BaseEntity {

    private String firstName;
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String phoneNumber;
    private boolean statusOnline;

    public Driver(String firstName, String lastName, String email, String password, String phoneNumber,boolean status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.statusOnline = status;
    }

    public Driver() {
    }
}
