package com.example.demo.service.base;

import com.example.demo.domain.base.IBaseEntity;

import java.util.List;

public interface IBaseService<T extends IBaseEntity> {

    T add(T entity);

    T findOne(Long id);

    List<T> findAll();

    T update(T entity, Long id);

    void delete(Long id);

}
