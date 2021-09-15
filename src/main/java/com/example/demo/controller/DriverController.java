package com.example.demo.controller;

import com.example.demo.controller.base.IBaseController;
import com.example.demo.domain.Driver;
import com.example.demo.model.request.DriverSignupRequest;
import com.example.demo.model.response.DriverSignupResponse;
import com.example.demo.service.IDriverService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.Helper;
import com.example.demo.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController implements IBaseController<DriverSignupResponse, Driver> {

    @Autowired
    private IDriverService driverService;

    @Autowired
    private ResourceBundleMessageSource messageSource;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseUtil> findOne(@RequestParam Long driverId) {
        Driver driver = driverService.findOne(driverId);
        DriverSignupResponse response = modelizeEntity(driver);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("retrieved.success", messageSource), response, 1L), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseEntity<ResponseUtil> findAll() {
        List<Driver> driver = driverService.findAll();
        List<DriverSignupResponse> response = modelizeList(driver);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("retrieved.success", messageSource), response, (long) response.size()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ResponseUtil> updateDriver(@RequestBody DriverSignupRequest request, @RequestParam Long driverId) {
        Driver driver = driverService.updateDriver(request, driverId);
        DriverSignupResponse response = modelizeEntity(driver);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("updated.success", messageSource), response, 1L), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<ResponseUtil> deleteDriver(@RequestParam Long driverId) {
        driverService.delete(driverId);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(200, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("deleted.success", messageSource), null, 0L), HttpStatus.OK);
    }


    @Override
    public DriverSignupResponse modelizeEntity(Driver entity) {
        DriverSignupResponse response = new DriverSignupResponse(entity.getId(), entity.getFirstName(), entity.getLastName(),
                entity.getEmail(), entity.getPhoneNumber());
        return response;
    }

    @Override
    public List<DriverSignupResponse> modelizeList(List<Driver> drivers) {
        List<DriverSignupResponse> responseList = new ArrayList<>();
        for (Iterator<Driver> iterator = drivers.iterator(); iterator.hasNext(); ) {
            Driver thisDriver = iterator.next();
            responseList.add(modelizeEntity(thisDriver));
        }
        return responseList;
    }
}
