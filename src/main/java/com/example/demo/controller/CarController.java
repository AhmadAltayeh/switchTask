package com.example.demo.controller;

import com.example.demo.controller.base.IBaseController;
import com.example.demo.domain.Car;
import com.example.demo.model.request.CarRequestModel;
import com.example.demo.model.response.CarResponseModel;
import com.example.demo.service.ICarService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.Helper;
import com.example.demo.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController implements IBaseController<CarResponseModel, Car> {

    @Autowired
    private ICarService carService;

    @Autowired
    ResourceBundleMessageSource messageSource;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseUtil> addCar(@RequestBody CarRequestModel requestModel, @RequestParam Long manuId) {
        Car car = carService.createCar(requestModel, manuId);
        CarResponseModel responseModel = modelizeEntity(car);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(201, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("created.success", messageSource), responseModel, 1L), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/select")
    public ResponseEntity<ResponseUtil> selectCar(@RequestParam Long carId, @RequestParam Long driverId) {
        carService.selectCar(carId, driverId);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("selected.success", messageSource), null, 0L), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/deselect")
    public ResponseEntity<ResponseUtil> deselectCar(@RequestParam Long carId, @RequestParam Long driverId) {
        carService.deselectCar(carId, driverId);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("deselected.success", messageSource), null, 0L), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseUtil> findOne(@RequestParam Long carId) {
        Car car = carService.findOne(carId);
        CarResponseModel responseModel = modelizeEntity(car);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("retrieved.success", messageSource), responseModel, 1L), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<ResponseUtil> findAll() {
        List<Car> car = carService.findAll();
        List<CarResponseModel> responseModel = modelizeList(car);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("retrieved.success", messageSource), responseModel, (long) responseModel.size()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ResponseUtil> updateCar(@RequestBody CarRequestModel requestModel, @RequestParam Long carId) {
        Car car = carService.updatedCar(requestModel, carId);
        CarResponseModel responseModel = modelizeEntity(car);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("updated.success", messageSource), responseModel, 1L), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<ResponseUtil> deleteCar(@RequestParam Long carId) {
        carService.delete(carId);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("deleted.success", messageSource), null, 0L), HttpStatus.OK);
    }

    @Override
    public CarResponseModel modelizeEntity(Car car) {
        CarResponseModel responseModel = new CarResponseModel(car.getId(), car.getLicensePlate(),
                car.getSeatCount(), car.isConvertible(), car.getRating(), car.getEngineType());
        return responseModel;
    }

    @Override
    public List<CarResponseModel> modelizeList(List<Car> car) {
        List<CarResponseModel> responseList = new ArrayList<>();
        for (Iterator<Car> iterator = car.iterator(); iterator.hasNext(); ) {
            Car thisCar = iterator.next();
            responseList.add(modelizeEntity(thisCar));
        }
        return responseList;
    }
}