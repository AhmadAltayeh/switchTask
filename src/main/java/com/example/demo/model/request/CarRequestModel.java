package com.example.demo.model.request;

import com.example.demo.domain.EngineType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestModel {

    private String licensePlate;
    private int seatCount;
    private boolean convertible;
    private int rating;
    private EngineType engineType;

}
