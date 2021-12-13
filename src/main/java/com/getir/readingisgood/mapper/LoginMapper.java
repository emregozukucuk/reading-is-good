package com.getir.readingisgood.mapper;

import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.LoginResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Mapper(componentModel = "spring")
public interface LoginMapper {
    LoginMapper INSTANCE = Mappers.getMapper( LoginMapper.class );

    LoginResponseModel loginEntityToLoginResponse(String authToken, BaseResponseModel baseResponseModel);
}
