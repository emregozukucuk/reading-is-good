package com.getir.readingisgood.enums;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public enum UpdateStockType {
    INCREASE("increase"), DECREASE("decrease");

    private final String updateType;

    UpdateStockType(String updateType){
        this.updateType = updateType;
    }

    public String getUpdateType() {
        return updateType;
    }
}
