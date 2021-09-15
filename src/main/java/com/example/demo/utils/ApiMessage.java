package com.example.demo.utils;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiMessage {
    private String message;
    private String messageCode;

    public ApiMessage(String message, String messageCode) {
        this.message = message;
        this.messageCode = messageCode;
    }
}
