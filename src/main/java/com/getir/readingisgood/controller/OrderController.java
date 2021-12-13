package com.getir.readingisgood.controller;

import com.getir.readingisgood.entity.OrderEntity;
import com.getir.readingisgood.model.request.CreateOrderRequestModel;
import com.getir.readingisgood.model.request.OrderByDateIntervalRequestModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.OrderResponseModelModel;
import com.getir.readingisgood.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderResponseModelModel> createOrder(@RequestBody @Valid CreateOrderRequestModel createOrderRequestModel){
        return ResponseEntity.ok(orderService.createOrder(createOrderRequestModel));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseModelModel> getOrder(@PathVariable String orderId){
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @PostMapping("/list-orders")
    public ResponseEntity<List<OrderResponseModelModel>> getOrderListByDateInterval(@RequestBody OrderByDateIntervalRequestModel orderByDateIntervalRequestModel){
        return ResponseEntity.ok(orderService.getOrderList(orderByDateIntervalRequestModel.getStartDate(), orderByDateIntervalRequestModel.getEndDate()));
    }
}
