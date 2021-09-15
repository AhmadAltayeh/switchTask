package com.example.demo.model.response;

import com.example.demo.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverSignupResponse extends BaseModel {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public DriverSignupResponse(Long id, String firstName, String lastName, String email, String phoneNumber) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
