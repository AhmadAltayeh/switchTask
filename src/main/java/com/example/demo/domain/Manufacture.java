package com.example.demo.domain;

import com.example.demo.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Manufacture extends BaseEntity {

    @Column(unique = true)
    private String name;

}
