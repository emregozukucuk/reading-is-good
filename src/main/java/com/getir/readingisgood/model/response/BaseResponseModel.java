package com.getir.readingisgood.model.response;

import com.getir.readingisgood.enums.ResponseStatusType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
public class BaseResponseModel {
    private ResponseStatusType responseStatusType;
    private String message;
    private Date createDate;
    private Date lastUpdateDate;
}
