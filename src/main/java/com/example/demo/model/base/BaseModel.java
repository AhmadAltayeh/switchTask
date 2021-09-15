package com.example.demo.model.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseModel {

    private Long id;

    public BaseModel(Long id) {
        this.id = id;
    }
}
