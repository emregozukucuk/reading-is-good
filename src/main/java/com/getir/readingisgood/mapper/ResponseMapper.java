package com.getir.readingisgood.mapper;

import com.getir.readingisgood.enums.ResponseStatusType;
import com.getir.readingisgood.model.response.BaseResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Mapper(componentModel = "spring", imports = { Date.class, ResponseStatusType.class })
public interface ResponseMapper {
    ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    @Mapping(target = "createDate", expression = "java(new Date())")
    @Mapping(target = "lastUpdateDate", expression = "java(new Date())")
    BaseResponseModel fieldToModel(ResponseStatusType responseStatusType, String message);
}
