package com.getir.readingisgood.builder.entity;

import com.getir.readingisgood.entity.OrderEntity;
import com.getir.readingisgood.enums.StatusType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class OrderEntityStubBuilder {

    public static OrderEntity create(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId("3333");
        orderEntity.setCreateDate(new Date());
        orderEntity.setBookId("1111");
        orderEntity.setCustomerId("2222");
        orderEntity.setLastUpdateDate(new Date());
        orderEntity.setStatusType(StatusType.ACTIVE);
        orderEntity.setPurchasedBookCount(1);
        return orderEntity;
    }

    public static List<OrderEntity> createList(){
        List<OrderEntity> orderEntities = new ArrayList<>();
        orderEntities.add(create());
        OrderEntity orderEntity = create();
        orderEntity.setBookId("11111");
        orderEntities.add(orderEntity);
        orderEntity.setBookId("111111");
        orderEntities.add(orderEntity);
        return orderEntities;
    }
}
