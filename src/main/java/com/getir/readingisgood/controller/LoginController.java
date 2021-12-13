package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.request.LoginRequestModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @ApiOperation("Login.")
    @PostMapping
    public void fakeLogin(@RequestBody LoginRequestModel loginRequestModel) {
        throw new IllegalStateException("This method shouldn't be called. It created for swagger requesting. It's implemented by Spring Security filters.");
    }
}
