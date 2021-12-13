package com.getir.readingisgood.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
public class OrderResponseModelModel extends BaseResponseModel {
    private String customerId;
    private String bookId;
    private Integer purchasedBookCount;
}
