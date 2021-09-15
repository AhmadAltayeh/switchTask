package com.example.demo.service.base;

import com.example.demo.domain.Driver;
import com.example.demo.domain.base.IBaseEntity;
import com.example.demo.exception.ValidationException;
import com.example.demo.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.util.List;

@Transactional
public abstract class BaseService<T extends IBaseEntity> implements IBaseService<T> {

    @Autowired
    ResourceBundleMessageSource messageSource;

    protected abstract JpaRepository<T, Long> getRepository();

    @Override
    @Transactional
    public T add(T entity) {
        preAdd(entity);
        insert(entity);
        return entity;
    }

    public void insert(T entity){
        try{
            getRepository().save(entity);
        }
        catch (ConstraintViolationException ex){
            throw new ValidationException(Helper.getLocaleMessage(Helper.getMessageKeyFromException(ex),messageSource));
        }
        catch(DataIntegrityViolationException ex){
            if(entity instanceof Driver)
                throw new ValidationException(Helper.getLocaleMessage("duplicate.driver",messageSource));
            else
                throw new ValidationException(Helper.getLocaleMessage("duplicate.entry",messageSource));
        }
    }

    @Override
    public T findOne(Long id) {
        if (getRepository().existsById(id))
            return getRepository().getById(id);
        else
            throw new ValidationException(Helper.getLocaleMessage("invalid.id",messageSource));
    }

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    @Transactional
    public T update(T entity, Long id) {
        try{
            preAdd(entity);
            return getRepository().save(entity);
        }
        catch (ConstraintViolationException ex) {
            throw new ValidationException(Helper.getLocaleMessage(Helper.getMessageKeyFromException(ex),messageSource));
        }
        catch (DataIntegrityViolationException ex){
            throw new ValidationException((Helper.getLocaleMessage("invalid.update",messageSource)));
        }
        catch (Exception ex){
            throw new ValidationException((Helper.getLocaleMessage("invalid.id",messageSource)));
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (getRepository().existsById(id)) {
            T entity = getRepository().getById(id);
            getRepository().delete(entity);
        }
        else
            throw new ValidationException(Helper.getLocaleMessage("invalid.id",messageSource));
    }

    public void preAdd(T entity) {
        entity.setDateCreated(ZonedDateTime.now());
    }
}
