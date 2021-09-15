package com.example.demo.exception;

import com.example.demo.utils.ApiMessage;
import org.springframework.context.support.ResourceBundleMessageSource;

public class ValidationException extends ApiException{


    public ValidationException(ApiMessage apiMessage) {
        super(apiMessage);
    }

    public ValidationException(String error){
        super(error);
    }


}
