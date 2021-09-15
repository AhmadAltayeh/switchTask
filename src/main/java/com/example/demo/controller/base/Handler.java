package com.example.demo.controller.base;

import com.example.demo.exception.ValidationException;
import com.example.demo.utils.Constants;
import com.example.demo.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ResponseUtil> handleValidation(ValidationException ex){
        return new ResponseEntity<ResponseUtil>(
                new ResponseUtil(400, Constants.FAILED_STATUS, ex.getApiMessage(), null, 0L), HttpStatus.BAD_REQUEST);
    }




}
