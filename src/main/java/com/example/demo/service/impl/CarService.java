package com.example.demo.service.impl;

import com.example.demo.domain.Car;
import com.example.demo.domain.Manufacture;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.request.CarRequestModel;
import com.example.demo.repository.ICarRepository;
import com.example.demo.repository.IDriverRepository;
import com.example.demo.repository.IManufactureRepository;
import com.example.demo.service.ICarService;
import com.example.demo.service.base.BaseService;
import com.example.demo.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarService extends BaseService<Car> implements ICarService {

    @Autowired
    private ICarRepository carRepository;

    @Autowired
    private IManufactureRepository manufactureRepository;

    @Autowired
    private IDriverRepository driverRepository;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @Override
    public Car createCar(CarRequestModel requestModel, Long manufactureId) {
        Car car = new Car(requestModel.getLicensePlate(), requestModel.getSeatCount(), requestModel.isConvertible(), requestModel.getRating(),
                requestModel.getEngineType());
        preAdd(car, manufactureId);
        return super.add(car);
    }

    @Override
    public Car updatedCar(CarRequestModel carReq, Long carId) {
        Car car = new Car(carReq.getLicensePlate(), carReq.getSeatCount(), carReq.isConvertible(), carReq.getRating(), carReq.getEngineType());
        preUpdate(car, carId);
        return super.update(car, carId);
    }

    @Override
    public List<Car> findByManufactureId(Long manuId) {
        return carRepository.findByManufactureId(manuId);
    }


    @Override
    public void selectCar(Long carId, Long driverId) {
        Car car = carRepository.getById(carId);
        if(car.getDriver() == null){
            car.setDriver(driverRepository.getById(driverId));
            preUpdate(car,carId);
            super.update(car,carId);
        }
        else
            throw new ValidationException(Helper.getLocaleMessage("car.inuse",messageSource));
    }

    @Override
    public void deselectCar(Long carId, Long driverId){
        Car car = carRepository.getById(carId);
        if(car.getDriver() != null){
            car.setDriver(null);
            preUpdate(car,carId);
            super.update(car,carId);
        }
        else
            throw new ValidationException(Helper.getLocaleMessage("car.use",messageSource));
    }

    @Override
    protected JpaRepository<Car, Long> getRepository() {
        return carRepository;
    }

    public void preAdd(Car entity, Long manuId) {
        Manufacture manufacture = manufactureRepository.getById(manuId);
        entity.setManufacture(manufacture);
    }

    public void preUpdate(Car entity, Long id) {
        //preAdd(entity, id);
        Car car = carRepository.getById(id);
        entity.setId(car.getId());
    }
}
