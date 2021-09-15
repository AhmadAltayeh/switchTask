package com.example.demo.model.response;

import com.example.demo.domain.EngineType;
import com.example.demo.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseModel extends BaseModel {

    private String licensePlate;
    private int seatCount;
    private boolean convertible;
    private int rating;
    private EngineType engineType;


    public CarResponseModel(Long id, String licensePlate, int seatCount, boolean convertible, int rating, EngineType engineType) {
        super(id);
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
    }
}
