package com.example.demo.model.response;

import com.example.demo.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ManufactureResponseModel extends BaseModel {

    private String name;
    private List<CarResponseModel> cars;

    public ManufactureResponseModel(Long id, String name, List<CarResponseModel> cars) {
        super(id);
        this.name = name;
        this.cars = cars;
    }
}
