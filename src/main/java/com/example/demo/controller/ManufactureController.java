package com.example.demo.controller;

import com.example.demo.controller.base.IBaseController;
import com.example.demo.domain.Car;
import com.example.demo.domain.Manufacture;
import com.example.demo.model.request.ManufactureRequestModel;
import com.example.demo.model.response.CarResponseModel;
import com.example.demo.model.response.ManufactureResponseModel;
import com.example.demo.service.ICarService;
import com.example.demo.service.IManufactureService;
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
@RequestMapping("/manufactures")
public class ManufactureController implements IBaseController<ManufactureResponseModel, Manufacture> {

    @Autowired
    private IManufactureService manufactureService;

    @Autowired
    private ICarService carService;

    @Autowired
    ResourceBundleMessageSource messageSource;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseUtil> addManufacture(@RequestBody ManufactureRequestModel requestModel) {
        Manufacture manufacture = manufactureService.createManufacture(requestModel);
        ManufactureResponseModel responseModel = modelizeEntity(manufacture);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(201, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("created.success", messageSource), responseModel, 1L), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseUtil> findOne(@RequestParam Long manuId) {
        Manufacture manufacture = manufactureService.findOne(manuId);
        ManufactureResponseModel responseModel = modelizeEntity(manufacture);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("retrieved.success", messageSource), responseModel, 1L), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<ResponseUtil> findAll() {
        List<Manufacture> manufacture = manufactureService.findAll();
        List<ManufactureResponseModel> responseModel = modelizeList(manufacture);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("retrieved.success", messageSource), responseModel, (long) responseModel.size()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ResponseUtil> updateManu(@RequestBody ManufactureRequestModel requestModel, @RequestParam Long manuId) {
        Manufacture manufacture = manufactureService.updateManufacture(requestModel, manuId);
        ManufactureResponseModel responseModel = modelizeEntity(manufacture);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("updated.success", messageSource), responseModel, 1L), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<ResponseUtil> deleteManu(@RequestParam Long manuId) {
        manufactureService.delete(manuId);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("deleted.success", messageSource), null, 0L), HttpStatus.OK);
    }

    @Override
    public ManufactureResponseModel modelizeEntity(Manufacture manufacture) {
        ManufactureResponseModel responseModel = new ManufactureResponseModel(manufacture.getId(), manufacture.getName(), mapManufactures(manufacture));
        return responseModel;
    }

    @Override
    public List<ManufactureResponseModel> modelizeList(List<Manufacture> manufacture) {
        List<ManufactureResponseModel> responseList = new ArrayList<>();
        for (Iterator<Manufacture> iterator = manufacture.iterator(); iterator.hasNext(); ) {
            Manufacture thisManufacture = iterator.next();
            responseList.add(modelizeEntity(thisManufacture));
        }
        return responseList;
    }

    private List<CarResponseModel> mapManufactures(Manufacture manufacture){
        List<Car> car = carService.findByManufactureId(manufacture.getId());
        List<CarResponseModel> responseList = new ArrayList<>();
        for (Iterator<Car> iterator = car.iterator(); iterator.hasNext(); ) {
            Car thisCar = iterator.next();
            CarResponseModel responseModel = new CarResponseModel(thisCar.getId(), thisCar.getLicensePlate(),
                    thisCar.getSeatCount(), thisCar.isConvertible(), thisCar.getRating(), thisCar.getEngineType());
            responseList.add(responseModel);
        }
        return responseList;
    }
}
