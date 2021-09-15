package com.example.demo.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverSignupRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;

}
