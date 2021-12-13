package com.getir.readingisgood.service;

import com.getir.readingisgood.builder.CreateOrderRequestModelStubBuilder;
import com.getir.readingisgood.builder.entity.OrderEntityStubBuilder;
import com.getir.readingisgood.entity.OrderEntity;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.model.request.CreateOrderRequestModel;
import com.getir.readingisgood.model.response.BookResponseModel;
import com.getir.readingisgood.model.response.OrderResponseModelModel;
import com.getir.readingisgood.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookService bookService;

    @Mock
    private CustomerService customerService;

    @Test
    void createOrder() {
        //given
        CreateOrderRequestModel orderRequestModel = CreateOrderRequestModelStubBuilder.create();

        //when
        when(bookService.updateBookStock(anyString(), anyInt(), anyString())).thenReturn(new BookResponseModel());
        when(orderRepository.save(any(OrderEntity.class))).thenReturn(OrderEntityStubBuilder.create());

        //then
        OrderResponseModelModel order = orderService.createOrder(orderRequestModel);

        assertThat(order.getBookId()).isEqualTo(orderRequestModel.getBookId());
        assertThat(order.getCustomerId()).isEqualTo(orderRequestModel.getCustomerId());
        assertThat(order.getPurchasedBookCount()).isEqualTo(orderRequestModel.getPurchasedBookCount());

    }

    @Test
    void getOrderById_should_throw_exception_when_order_not_found_with_given_id() {
        //given
        String orderId = "3333";

        //when
        when(orderRepository.findByOrderId(anyString())).thenReturn(Optional.empty());

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            orderService.getOrderById(orderId);
        });

        assertThat(businessException.getMessage()).isEqualTo("Order not found with given id: " + orderId);
    }

    @Test
    void getOrderListByDateInterval() {
        //given
        Date startDate = new Date();
        Date endDate = new Date();

        //when
        when(orderRepository.findOrderListByDateInterval(any(Date.class), any(Date.class))).thenReturn(Optional.empty());

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            orderService.getOrderList(startDate, endDate);
        });

        assertThat(businessException.getMessage()).isEqualTo("Order not found with given between: " + startDate + " and " + endDate);

    }

    @Test
    void getOrder() {
        //given
        String orderId = "3333";

        //when
        when(orderRepository.findByOrderId(anyString())).thenReturn(Optional.of(OrderEntityStubBuilder.create()));

        //then
        OrderResponseModelModel order = orderService.getOrder(orderId);

        assertThat(order).isNotNull();

    }

    @Test
    void getOrderList() {
        //given
        Date startDate = new Date();
        Date endDate = new Date();

        //when
        when(orderRepository.findOrderListByDateInterval(any(Date.class), any(Date.class))).thenReturn(Optional.of(OrderEntityStubBuilder.createList()));

        //then
        List<OrderResponseModelModel> orderList = orderService.getOrderList(startDate, endDate);

        assertThat(orderList).isNotNull();

    }

    @Test
    void getOrderListByCustomerId_should_throw_exception_when_order_not_found_with_given_id() {
        //given
        String customerId = "1111";

        //when
        when(orderRepository.findAllByCustomerId(anyString())).thenReturn(Optional.empty());

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            orderService.getOrderListByCustomerId(customerId);
        });

        assertThat(businessException.getMessage()).isEqualTo("Order not found with given Id: " + customerId);

    }
}