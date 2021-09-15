package com.example.demo.domain.base;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

public interface IBaseEntity {

    Serializable getId();

    void setId(Long id);

    void setDateCreated(ZonedDateTime date);

    ZonedDateTime getDateCreated();

}
