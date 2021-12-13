package com.getir.readingisgood.mapper;

import com.getir.readingisgood.entity.CustomerEntity;
import com.getir.readingisgood.enums.StatusType;
import com.getir.readingisgood.model.request.CustomerRegisterRequestModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.CustomerResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Mapper(componentModel = "spring", imports = { Date.class, StatusType.class })
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    @Mapping(target = "createDate", expression = "java(new Date())")
    @Mapping(target = "lastUpdateDate", expression = "java(new Date())")
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "status", expression = "java(StatusType.ACTIVE)")
    @Mapping(target = "accountNonExpired", constant = "true")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "credentialsNonExpired", constant = "true")
    @Mapping(target = "password", source = "encryptedPassword")
    CustomerEntity customerModelToCustomerEntity(CustomerRegisterRequestModel customerRegisterRequestModel, String encryptedPassword);

    @Mapping(target = "createDate", source = "customerEntity.createDate")
    @Mapping(target = "lastUpdateDate", source = "customerEntity.lastUpdateDate")
    CustomerResponseModel customerEntityToCustomerModel(BaseResponseModel baseResponseModel, CustomerEntity customerEntity);

}
