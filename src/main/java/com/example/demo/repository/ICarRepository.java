package com.example.demo.repository;

import com.example.demo.domain.Car;
import com.example.demo.repository.base.IBaseRepository;

import java.util.List;

public interface ICarRepository extends IBaseRepository<Car,Long> {

    List<Car> findByManufactureId(Long manuId);

    Car findByDriverId(Long driverId);
}
