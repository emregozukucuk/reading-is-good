package com.getir.readingisgood.mapper;

import com.getir.readingisgood.entity.OrderEntity;
import com.getir.readingisgood.enums.StatusType;
import com.getir.readingisgood.model.request.CreateOrderRequestModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.BookResponseModel;
import com.getir.readingisgood.model.response.CustomerResponseModel;
import com.getir.readingisgood.model.response.OrderResponseModelModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Mapper(componentModel = "spring", imports = { Date.class, StatusType.class })
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "createDate", expression = "java(new Date())")
    @Mapping(target = "lastUpdateDate", expression = "java(new Date())")
    @Mapping(target = "statusType", expression = "java(StatusType.ACTIVE)")
    OrderEntity orderModelToOrderEntity(CreateOrderRequestModel createOrderRequestModel);

    @Mapping(target = "createDate", source = "orderEntity.createDate")
    @Mapping(target = "lastUpdateDate", source = "orderEntity.lastUpdateDate")
    OrderResponseModelModel orderEntityToOrderModel(BaseResponseModel baseResponseModel, OrderEntity orderEntity);
}
