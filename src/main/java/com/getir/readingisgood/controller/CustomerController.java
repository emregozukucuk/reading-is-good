package com.getir.readingisgood.controller;
import com.getir.readingisgood.model.request.CustomerRegisterRequestModel;
import com.getir.readingisgood.model.response.CustomerResponseModel;
import com.getir.readingisgood.model.response.OrderResponseModelModel;
import com.getir.readingisgood.service.CustomerService;
import com.getir.readingisgood.service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {


    private final RegisterService registerService;
    private final CustomerService customerService;

    public CustomerController(RegisterService registerService, CustomerService customerService) {
        this.registerService = registerService;
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerResponseModel> registerCustomer(@RequestBody @Validated CustomerRegisterRequestModel customerRegisterRequestModel) {
        return ResponseEntity.ok(registerService.registerCustomer(customerRegisterRequestModel));
    }

    @GetMapping("/get-orders/{customerId}")
    public ResponseEntity<List<OrderResponseModelModel>> getCustomerOrders(@PathVariable String customerId, @RequestParam(defaultValue = "0") int page){
        return ResponseEntity.ok(customerService.getCustomerOrders(customerId, page));
    }
}
