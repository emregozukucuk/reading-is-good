package com.getir.readingisgood.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
public class BookResponseModel extends BaseResponseModel{
    private String bookName;
    private BigDecimal price;
    private Integer stockCount;
}
