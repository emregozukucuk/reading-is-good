package com.getir.readingisgood.model.response;

import lombok.Getter;
import lombok.Setter;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
public class LoginResponseModel extends BaseResponseModel {
    private String authToken;
}
