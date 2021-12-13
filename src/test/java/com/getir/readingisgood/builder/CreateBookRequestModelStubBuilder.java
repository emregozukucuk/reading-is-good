package com.getir.readingisgood.builder;

import com.getir.readingisgood.model.request.CreateBookRequestModel;

import java.math.BigDecimal;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class CreateBookRequestModelStubBuilder {

    public static CreateBookRequestModel create(){
        CreateBookRequestModel createBookRequestModel = new CreateBookRequestModel();
        createBookRequestModel.setBookName("Tutunamayanlar");
        createBookRequestModel.setPrice(BigDecimal.valueOf(45.0));
        createBookRequestModel.setStockCount(10);
        return createBookRequestModel;
    }
}
