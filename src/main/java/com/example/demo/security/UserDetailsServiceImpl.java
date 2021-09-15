package com.example.demo.security;

import com.example.demo.domain.Driver;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.IDriverRepository;
import com.example.demo.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IDriverRepository driverRepository;

    @Autowired
    ResourceBundleMessageSource messageSource;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Driver driver = driverRepository.findByEmail(s);
        if(driver!=null){
            return UserDetailsImpl.build(driver);
        }
        else
            throw new ValidationException(Helper.getLocaleMessage("invalid.email",messageSource));

    }
}
