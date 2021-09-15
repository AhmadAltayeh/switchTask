package com.example.demo.exception;

import com.example.demo.utils.ApiMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiException extends RuntimeException {

    private String error;
    private ApiMessage apiMessage;

    public ApiException(ApiMessage message) {
        this.apiMessage = message;
    }

    public ApiException(String error) {
        super(error);
        this.error = error;
    }
}
