package com.getir.readingisgood.builder;

import com.getir.readingisgood.model.request.CustomerRegisterRequestModel;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class CustomerRegisterRequestModelStubBuilder {

    public static CustomerRegisterRequestModel create(){
        CustomerRegisterRequestModel registerRequestModel = new CustomerRegisterRequestModel();
        registerRequestModel.setEmail("test@test.com");
        registerRequestModel.setName("test");
        registerRequestModel.setPassword("test");
        registerRequestModel.setSurname("test");
        return registerRequestModel;
    }
}
