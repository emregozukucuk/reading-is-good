package com.getir.readingisgood.model.response;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.getir.readingisgood.enums.StatusType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
public class CustomerResponseModel extends BaseResponseModel {
    private String name;
    private String surname;
    private String email;
    private Boolean enabled;
    private StatusType status;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
}
