package com.example.demo.domain;

import com.example.demo.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Car extends BaseEntity {

    @Column(unique = true)
    private String licensePlate;
    private int seatCount;
    private boolean convertible;
    private int rating;
    private EngineType engineType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Manufacture manufacture;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    public Car(String licensePlate, int seatCount, boolean convertible, int rating, EngineType engineType) {
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
    }

    public Car() {
    }
}
