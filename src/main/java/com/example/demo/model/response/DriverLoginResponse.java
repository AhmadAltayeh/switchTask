package com.example.demo.model.response;

import com.example.demo.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverLoginResponse extends BaseModel {

    private String email;
    private String token;

    public DriverLoginResponse(Long id, String email, String token) {
        super(id);
        this.email = email;
        this.token = token;
    }
}
