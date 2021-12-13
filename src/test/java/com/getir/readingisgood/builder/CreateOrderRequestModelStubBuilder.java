package com.getir.readingisgood.builder;

import com.getir.readingisgood.model.request.CreateOrderRequestModel;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class CreateOrderRequestModelStubBuilder {

    public static CreateOrderRequestModel create(){
        CreateOrderRequestModel orderRequestModel = new CreateOrderRequestModel();
        orderRequestModel.setCustomerId("1111");
        orderRequestModel.setBookId("2222");
        orderRequestModel.setPurchasedBookCount(1);
        return orderRequestModel;
    }
}
