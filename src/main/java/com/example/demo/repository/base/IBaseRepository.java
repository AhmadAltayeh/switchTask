package com.example.demo.repository.base;

import com.example.demo.domain.base.IBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseRepository<T extends IBaseEntity, ID> extends JpaRepository<T,ID> {


}
