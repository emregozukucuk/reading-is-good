package com.getir.readingisgood.mapper;

import com.getir.readingisgood.entity.AuditEntity;
import com.getir.readingisgood.enums.OperationType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Mapper(componentModel = "spring", imports = { Date.class})
public interface AuditMapper {
    AuditMapper INSTANCE = Mappers.getMapper(AuditMapper.class);

    @Mapping(target = "createDate", expression = "java(new Date())")
    AuditEntity fieldToAuditEntity(String customerEmail, OperationType operation, String message);

}