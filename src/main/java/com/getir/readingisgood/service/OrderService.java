package com.getir.readingisgood.service;

import com.getir.readingisgood.constants.AppConstants;
import com.getir.readingisgood.entity.OrderEntity;
import com.getir.readingisgood.enums.ResponseStatusType;
import com.getir.readingisgood.enums.UpdateStockType;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.mapper.OrderMapper;
import com.getir.readingisgood.mapper.ResponseMapper;
import com.getir.readingisgood.model.request.CreateOrderRequestModel;
import com.getir.readingisgood.model.response.BaseResponseModel;
import com.getir.readingisgood.model.response.OrderResponseModelModel;
import com.getir.readingisgood.repository.OrderRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookService bookService;
    private final CustomerService customerService;

    public OrderService(OrderRepository orderRepository, BookService bookService, @Lazy CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.bookService = bookService;
        this.customerService = customerService;
    }

    @Transactional
    public OrderResponseModelModel createOrder(CreateOrderRequestModel createOrderRequestModel) {
        customerService.checkCustomerIdIsExist(createOrderRequestModel.getCustomerId());
        bookService.checkBookIdIsExist(createOrderRequestModel.getBookId());
        OrderEntity orderEntity = OrderMapper.INSTANCE.orderModelToOrderEntity(createOrderRequestModel);
        bookService.updateBookStock(createOrderRequestModel.getBookId(), createOrderRequestModel.getPurchasedBookCount(), UpdateStockType.DECREASE.getUpdateType());
        orderRepository.save(orderEntity);
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.SUCCESS, AppConstants.CREATE_ORDER_SUCCESS);
        return OrderMapper.INSTANCE.orderEntityToOrderModel(baseResponseModel, orderEntity);
    }

    public OrderEntity getOrderById(String orderId) {
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new BusinessException("Order not found with given id: " + orderId));
    }

    public List<OrderEntity> getOrderListByDateInterval(Date startDate, Date endDate) {
        return orderRepository.findOrderListByDateInterval(startDate, endDate)
                .orElseThrow(() -> new BusinessException("Order not found with given between: " + startDate + " and "+ endDate));
    }

    public OrderResponseModelModel getOrder(String orderId) {
        final OrderEntity orderEntity = getOrderById(orderId);
        BaseResponseModel baseResponseModel = ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.SUCCESS, AppConstants.SUCCESS);
        return OrderMapper.INSTANCE.orderEntityToOrderModel(baseResponseModel, orderEntity);
    }

    public List<OrderResponseModelModel> getOrderList(Date startDate, Date endDate) {
        List<OrderResponseModelModel> orderResponseModelModels = new ArrayList<>();
        final List<OrderEntity> orderListByDateInterval = getOrderListByDateInterval(startDate, endDate);
        orderListByDateInterval
                .stream()
                .forEach(orderEntity -> orderResponseModelModels
                        .add(OrderMapper.INSTANCE.orderEntityToOrderModel(ResponseMapper.INSTANCE.fieldToModel(ResponseStatusType.SUCCESS, AppConstants.SUCCESS), orderEntity)));

        return orderResponseModelModels;
    }

    public List<OrderEntity> getOrderListByCustomerId(String customerId){
        return orderRepository.findAllByCustomerId(customerId)
                .orElseThrow(() -> new BusinessException("Order not found with given Id: " + customerId));
    }

}
