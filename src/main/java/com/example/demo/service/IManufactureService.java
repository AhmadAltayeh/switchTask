package com.example.demo.service;


import com.example.demo.domain.Manufacture;
import com.example.demo.model.request.ManufactureRequestModel;
import com.example.demo.service.base.IBaseService;

public interface IManufactureService extends IBaseService<Manufacture> {

    Manufacture createManufacture(ManufactureRequestModel manufactureRequestModel);

    Manufacture updateManufacture(ManufactureRequestModel requestModel,Long manuId);
}
