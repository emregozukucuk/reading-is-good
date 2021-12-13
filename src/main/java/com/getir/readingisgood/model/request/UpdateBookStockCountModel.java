package com.getir.readingisgood.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
public class UpdateBookStockCountModel {
    @NotNull
    private String bookId;
    @NotNull
    private Integer updatedStockCount;
}
