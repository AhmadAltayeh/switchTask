package com.example.demo.service.impl;

import com.example.demo.domain.Manufacture;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.request.ManufactureRequestModel;
import com.example.demo.repository.IManufactureRepository;
import com.example.demo.service.IManufactureService;
import com.example.demo.service.base.BaseService;
import com.example.demo.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ManufactureService extends BaseService<Manufacture> implements IManufactureService {

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @Autowired
    private IManufactureRepository manufactureRepository;

    @Override
    protected JpaRepository<Manufacture, Long> getRepository() {
        return manufactureRepository;
    }

    @Override
    public Manufacture createManufacture(ManufactureRequestModel manufactureRequestModel) {
        Manufacture manufacture = new Manufacture(manufactureRequestModel.getName());
        return super.add(manufacture);
    }

    @Override
    public Manufacture updateManufacture(ManufactureRequestModel requestModel, Long manuId) {
        try {
            Manufacture manufacture = new Manufacture(requestModel.getName());
            preUpdate(manufacture, manuId);
            return super.update(manufacture, manuId);
        }
        catch (EntityNotFoundException ex) {
            throw new ValidationException(Helper.getLocaleMessage("invalid.id", messageSource));
        }
    }

    public void preUpdate(Manufacture entity, Long id){
            Manufacture thisManufacture = manufactureRepository.getById(id);
            entity.setId(thisManufacture.getId());
    }
}
