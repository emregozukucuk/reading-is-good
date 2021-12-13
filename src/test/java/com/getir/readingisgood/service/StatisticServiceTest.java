package com.getir.readingisgood.service;

import com.getir.readingisgood.builder.entity.BookEntityStubBuilder;
import com.getir.readingisgood.builder.entity.OrderEntityStubBuilder;
import com.getir.readingisgood.model.response.MonthlyStatisticResponseModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class StatisticServiceTest {

    @InjectMocks
    private StatisticService statisticService;

    @Mock
    private OrderService orderService;

    @Mock
    private CustomerService customerService;

    @Mock
    private BookService bookService;

    @Test
    void getMonthlyStatistic() {
        //given
        String customerId = "1111";

        //when
        doNothing().when(customerService).checkCustomerIdIsExist(anyString());
        when(orderService.getOrderListByCustomerId(anyString())).thenReturn(OrderEntityStubBuilder.createList());
        when(bookService.getBookById(anyString())).thenReturn(BookEntityStubBuilder.create());

        //then
        List<MonthlyStatisticResponseModel> monthlyStatistic = statisticService.getMonthlyStatistic(customerId);

        assertThat(monthlyStatistic).isNotNull();
    }
}