package com.getir.readingisgood.builder;

import com.getir.readingisgood.enums.ResponseStatusType;
import com.getir.readingisgood.model.response.BaseResponseModel;

import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class BaseResponseModelStubBuilder {

    public static BaseResponseModel create(){
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setResponseStatusType(ResponseStatusType.SUCCESS);
        baseResponseModel.setMessage("test");
        baseResponseModel.setCreateDate(new Date());
        baseResponseModel.setLastUpdateDate(new Date());
        return baseResponseModel;
    }
}
