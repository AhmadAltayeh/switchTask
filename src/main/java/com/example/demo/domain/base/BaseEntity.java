package com.example.demo.domain.base;

import com.example.demo.domain.base.IBaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
public class BaseEntity implements IBaseEntity, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;
    private ZonedDateTime dateCreated;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setDateCreated(ZonedDateTime date) {
        this.dateCreated = date;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
