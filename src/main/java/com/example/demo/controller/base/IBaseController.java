package com.example.demo.controller.base;

import com.example.demo.domain.base.IBaseEntity;
import com.example.demo.model.base.BaseModel;

import java.util.List;

public interface IBaseController<E extends BaseModel, T extends IBaseEntity> {

    E modelizeEntity(T entity);

    List<E> modelizeList(List<T> entity);
}
