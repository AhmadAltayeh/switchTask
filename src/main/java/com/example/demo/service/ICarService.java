package com.example.demo.service;

import com.example.demo.domain.Car;
import com.example.demo.model.request.CarRequestModel;
import com.example.demo.model.response.CarResponseModel;
import com.example.demo.service.base.IBaseService;

import java.util.List;

public interface ICarService extends IBaseService<Car> {

    Car createCar(CarRequestModel requestModel,Long manufactureId);

    Car updatedCar(CarRequestModel carRequestModel, Long carId);

    List<Car> findByManufactureId(Long manuId);

    void selectCar(Long carId,Long driverId);

    void deselectCar(Long carId,Long driverId);
}
