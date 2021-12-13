package com.getir.readingisgood.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
public class MonthlyStatisticResponseModel {
    private String year;
    private String month;
    private int totalOrderCount;
    private int totalBookCount;
    private BigDecimal totalPurchasedAmount;

    public MonthlyStatisticResponseModel(){}

    public MonthlyStatisticResponseModel(String year, String month, int totalOrderCount, int totalBookCount, BigDecimal totalPurchasedAmount) {
        this.year = year;
        this.month = month;
        this.totalOrderCount = totalOrderCount;
        this.totalBookCount = totalBookCount;
        this.totalPurchasedAmount = totalPurchasedAmount;
    }
}
