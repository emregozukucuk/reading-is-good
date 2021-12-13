package com.getir.readingisgood.service;

import com.getir.readingisgood.builder.entity.CustomerEntityStubBuilder;
import com.getir.readingisgood.builder.entity.OrderEntityStubBuilder;
import com.getir.readingisgood.exception.BusinessException;
import com.getir.readingisgood.model.response.OrderResponseModelModel;
import com.getir.readingisgood.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderService orderService;

    @Test
    void getCustomerById_should_throw_exception_when_customer_not_found_with_given_id() {
        //given
        String customerId = "2222";

        //when
        when(customerRepository.findByCustomerId(anyString())).thenReturn(Optional.empty());

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            customerService.getCustomerById(customerId);
        });

        assertThat(businessException.getMessage()).isEqualTo("Customer not found with given id: " + customerId);
    }

    @Test
    void checkCustomerIdIsExist_should_throw_exception_when_customer_not_found_with_given_id() {
        //given
        String customerId = "2222";

        //when
        when(customerRepository.findByCustomerId(anyString())).thenReturn(Optional.empty());

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            customerService.getCustomerById(customerId);
        });

        assertThat(businessException.getMessage()).isEqualTo("Customer not found with given id: " + customerId);
    }

    @Test
    void getCustomerOrders() {
        //given
        String customerId = "2222";
        int page = 1;

        //when
        when(customerRepository.findByCustomerId(anyString())).thenReturn(Optional.of(CustomerEntityStubBuilder.create()));
        when(orderService.getOrderListByCustomerId(anyString())).thenReturn(OrderEntityStubBuilder.createList());

        //then
        List<OrderResponseModelModel> customerOrders = customerService.getCustomerOrders(customerId, page);
        assertThat(customerOrders.size()).isEqualTo(2);
    }

    @Test
    void getCustomerOrders_should_throw_when_order_list_is_empty() {
        //given
        String customerId = "2222";
        int page = 1;

        //when
        when(customerRepository.findByCustomerId(anyString())).thenReturn(Optional.of(CustomerEntityStubBuilder.create()));
        when(orderService.getOrderListByCustomerId(anyString())).thenReturn(new ArrayList<>());

        //then
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            customerService.getCustomerOrders(customerId, page);
        });

        assertThat(businessException.getMessage()).isEqualTo("No order found with for customerId: " + customerId);
    }

    @Test
    void getCustomerOrders_should_return_all_order_list_when_page_greater_then_order_list() {
        //given
        String customerId = "2222";
        int page = 8;

        //when
        when(customerRepository.findByCustomerId(anyString())).thenReturn(Optional.of(CustomerEntityStubBuilder.create()));
        when(orderService.getOrderListByCustomerId(anyString())).thenReturn(OrderEntityStubBuilder.createList());

        //then
        List<OrderResponseModelModel> customerOrders = customerService.getCustomerOrders(customerId, page);
        assertThat(customerOrders.size()).isEqualTo(3);
    }

    @Test
    void getCustomerOrders_should_return_all_order_list_when_page_less_then_zero() {
        //given
        String customerId = "2222";
        int page = -1;

        //when
        when(customerRepository.findByCustomerId(anyString())).thenReturn(Optional.of(CustomerEntityStubBuilder.create()));
        when(orderService.getOrderListByCustomerId(anyString())).thenReturn(OrderEntityStubBuilder.createList());

        //then
        List<OrderResponseModelModel> customerOrders = customerService.getCustomerOrders(customerId, page);
        assertThat(customerOrders.size()).isEqualTo(3);
    }

    @Test
    void getCustomerOrders_should_return_all_order_list_when_page_equals_zero() {
        //given
        String customerId = "2222";
        int page = 0;

        //when
        when(customerRepository.findByCustomerId(anyString())).thenReturn(Optional.of(CustomerEntityStubBuilder.create()));
        when(orderService.getOrderListByCustomerId(anyString())).thenReturn(OrderEntityStubBuilder.createList());

        //then
        List<OrderResponseModelModel> customerOrders = customerService.getCustomerOrders(customerId, page);
        assertThat(customerOrders.size()).isEqualTo(3);
    }
}