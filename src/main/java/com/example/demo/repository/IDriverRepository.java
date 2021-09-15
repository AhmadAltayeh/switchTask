package com.example.demo.repository;

import com.example.demo.domain.Driver;
import com.example.demo.repository.base.IBaseRepository;

public interface IDriverRepository extends IBaseRepository<Driver,Long> {

    Driver findByEmail(String s);

}
