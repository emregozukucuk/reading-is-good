package com.getir.readingisgood.service;

import com.getir.readingisgood.entity.BookEntity;
import com.getir.readingisgood.entity.OrderEntity;
import com.getir.readingisgood.model.response.MonthlyStatisticResponseModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Service
public class StatisticService {

    public static final int INCREASE_ORDER_BY_ONE = 1;
    private final OrderService orderService;
    private final CustomerService customerService;
    private final BookService bookService;

    public StatisticService(OrderService orderService, CustomerService customerService, BookService bookService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.bookService = bookService;
    }

    public List<MonthlyStatisticResponseModel> getMonthlyStatistic(String customerId) {
        customerService.checkCustomerIdIsExist(customerId);
        List<OrderEntity> orderListByCustomerIdDateInterval = orderService.getOrderListByCustomerId(customerId);
        return calculateStatistic(orderListByCustomerIdDateInterval);
    }

    private List<MonthlyStatisticResponseModel> calculateStatistic(List<OrderEntity> orderListByCustomerIdDateInterval) {
        Map<String, MonthlyStatisticResponseModel> responseModelHashMap = new HashMap<>();
        for (OrderEntity order : orderListByCustomerIdDateInterval) {
        //year-month key
            LocalDate created = order.getCreateDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Integer purchasedBookCount = order.getPurchasedBookCount();
            String key = created.getYear() + "-" + created.getMonth().name();
            MonthlyStatisticResponseModel monthlyStatisticResponseModel = responseModelHashMap.get(key);
            if (monthlyStatisticResponseModel == null) {
                monthlyStatisticResponseModel = new MonthlyStatisticResponseModel(String.valueOf(created.getYear())
                        ,created.getMonth().name()
                        ,1
                        , purchasedBookCount
                        ,getTotalPurchasedAmount(order.getBookId(), purchasedBookCount));

            }else {
                monthlyStatisticResponseModel.setTotalBookCount(monthlyStatisticResponseModel.getTotalBookCount()+purchasedBookCount);
                monthlyStatisticResponseModel.setTotalOrderCount(monthlyStatisticResponseModel.getTotalOrderCount()+ INCREASE_ORDER_BY_ONE);
                monthlyStatisticResponseModel.setTotalPurchasedAmount(monthlyStatisticResponseModel.getTotalPurchasedAmount().add(getTotalPurchasedAmount(order.getBookId(), purchasedBookCount)));
            }
            responseModelHashMap.put(key, monthlyStatisticResponseModel);
        }
        return new ArrayList<>(responseModelHashMap.values());
    }

    private BigDecimal getTotalPurchasedAmount(String bookId, int purchasedBookCount) {
        BookEntity bookById = bookService.getBookById(bookId);
        return bookById.getPrice().multiply(BigDecimal.valueOf(purchasedBookCount));
    }
}
