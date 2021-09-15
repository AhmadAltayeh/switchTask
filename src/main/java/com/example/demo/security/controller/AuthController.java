package com.example.demo.security.controller;

import com.example.demo.controller.base.IBaseController;
import com.example.demo.domain.Driver;
import com.example.demo.model.request.DriverLoginRequest;
import com.example.demo.model.request.DriverSignupRequest;
import com.example.demo.model.response.DriverLoginResponse;
import com.example.demo.model.response.DriverSignupResponse;
import com.example.demo.security.JwtUtils;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.IDriverService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.Helper;
import com.example.demo.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController implements IBaseController<DriverSignupResponse, Driver> {

    @Autowired
    private IDriverService driverService;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseUtil> createUser(@RequestBody DriverSignupRequest request) {
        Driver driver = driverService.createDriver(request);
        DriverSignupResponse response = modelizeEntity(driver);
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(201, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("created.success", messageSource), response, 1L), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<ResponseUtil> login(@RequestBody DriverLoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new ResponseEntity<ResponseUtil>(new ResponseUtil(201, Constants.SUCCESS_STATUS,
                Helper.getLocaleMessage("login.success", messageSource),
                new DriverLoginResponse(driverService.findByEmail(request.getEmail()).getId(), request.getEmail(), token), 1L), HttpStatus.OK);
    }

    @Override
    public DriverSignupResponse modelizeEntity(Driver entity) {
        DriverSignupResponse response = new DriverSignupResponse(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPhoneNumber());
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
