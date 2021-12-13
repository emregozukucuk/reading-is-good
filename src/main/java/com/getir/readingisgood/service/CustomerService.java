package com.getir.readingisgood.service;

import com.getir.readingisgood.constants.AppConstants;
import com.getir.readingisgood.entity.CustomerEntity;
import com.getir.readingisgood.entity.OrderEntity;
import com.getir.readingisgood.enums.ResponseStatusType;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.mapper.CustomerMapper;
import com.getir.readingisgood.mapper.OrderMapper;
import com.getir.readingisgood.mapper.ResponseMapper;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.CustomerResponseModel;
import com.getir.readingisgood.model.response.OrderResponseModelModel;
import com.getir.readingisgood.repository.CustomerRepository;
import com.getir.readingisgood.utils.AppUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Slf4j
@Service
public class CustomerService {

    public static final int PAGE_ITEM_COUNT = 2;
    public static final String ORDER_NOT_FOUND_ERROR = "No order found error";
    private final CustomerRepository customerRepository;
    private final OrderService orderService;

    public CustomerService(CustomerRepository customerRepository, @Lazy OrderService orderService) {
        this.customerRepository = customerRepository;
        this.orderService = orderService;
    }

    public CustomerEntity getCustomerById(String customerId) {
        return customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new BusinessException("Customer not found with given id: " + customerId));
    }

    public void checkCustomerIdIsExist(String customerId) {
        customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new BusinessException("Customer not found with given id: " + customerId));
    }

    public List<OrderResponseModelModel> getCustomerOrders(String customerId, int page) {
        checkCustomerIdIsExist(customerId);
        List<OrderResponseModelModel> orderResponseModelModels = new ArrayList<>();
        List<OrderEntity> orderListByCustomerId = orderService.getOrderListByCustomerId(customerId);
        List<OrderEntity> paginatedList = null;

        if (orderListByCustomerId.isEmpty()) {
            log.error("CustomerService :: getCustomerOrders :: customerId = {} :: page = {} :: message = {}", customerId, page, ORDER_NOT_FOUND_ERROR);
            throw new BusinessException("No order found with for customerId: " + customerId);
        }

        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.SUCCESS, AppConstants.SUCCESS);

        paginatedList = AppUtil.getPage(orderListByCustomerId, page, PAGE_ITEM_COUNT);

        paginatedList
                .stream()
                .forEach(orderEntity -> orderResponseModelModels
                        .add(OrderMapper.INSTANCE.orderEntityToOrderModel(baseResponseModel, orderEntity)));
        return orderResponseModelModels;
    }

}
