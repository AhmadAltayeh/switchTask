package com.example.demo.service;

import com.example.demo.domain.Driver;
import com.example.demo.model.request.DriverSignupRequest;
import com.example.demo.service.base.IBaseService;

public interface IDriverService extends IBaseService<Driver> {

    Driver createDriver(DriverSignupRequest signupRequest);

    Driver updateDriver(DriverSignupRequest signupRequest,Long driverId);

    Driver findByEmail(String s);



}
