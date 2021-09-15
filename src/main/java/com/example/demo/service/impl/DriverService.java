package com.example.demo.service.impl;

import com.example.demo.domain.Car;
import com.example.demo.domain.Driver;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.request.DriverSignupRequest;
import com.example.demo.repository.ICarRepository;
import com.example.demo.repository.IDriverRepository;
import com.example.demo.security.JwtUtils;
import com.example.demo.service.IDriverService;
import com.example.demo.service.base.BaseService;
import com.example.demo.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DriverService extends BaseService<Driver> implements IDriverService {


    @Autowired
    private IDriverRepository driverRepository;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @Autowired
    ICarRepository carRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Driver createDriver(DriverSignupRequest signupRequest) {
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        Driver driver = new Driver(signupRequest.getFirstName(), signupRequest.getLastName(), signupRequest.getEmail(),
                signupRequest.getPassword(), signupRequest.getPhoneNumber(),true);
        return super.add(driver);
    }

    @Override
    public Driver updateDriver(DriverSignupRequest signupRequest, Long driverId) {
        Driver driver = new Driver(signupRequest.getFirstName(),signupRequest.getLastName(),signupRequest.getEmail(),
                signupRequest.getPassword(),signupRequest.getPhoneNumber(),true);
        preUpdate(driver,driverId);
        return super.update(driver,driverId);
    }

    @Override
    public Driver findByEmail(String s) {
        return driverRepository.findByEmail(s);
    }


    @Override
    protected JpaRepository<Driver, Long> getRepository() {
        return driverRepository;
    }

    public void preUpdate(Driver entity, Long driverId) {
        //preAdd(entity, id);
        Driver driver = driverRepository.getById(driverId);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setId(driver.getId());
    }
}
