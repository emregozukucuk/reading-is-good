package com.getir.readingisgood.builder.entity;

import com.getir.readingisgood.entity.CustomerEntity;
import com.getir.readingisgood.enums.StatusType;

import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class CustomerEntityStubBuilder {

    public static CustomerEntity create(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId("2222");
        customerEntity.setCreateDate(new Date());
        customerEntity.setAccountNonExpired(true);
        customerEntity.setEnabled(true);
        customerEntity.setEmail("test@test.com");
        customerEntity.setName("test");
        customerEntity.setPassword("test");
        customerEntity.setStatus(StatusType.ACTIVE);
        customerEntity.setSurname("test");
        customerEntity.setAccountNonLocked(true);
        customerEntity.setLastUpdateDate(new Date());
        return customerEntity;
    }
}
