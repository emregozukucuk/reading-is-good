package com.getir.readingisgood.model.request;

import com.getir.readingisgood.validator.Email;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
public class LoginRequestModel {
    @Email
    private String email;
    @NotNull
    private String password;
}
