package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    Optional<OrderEntity> findByOrderId(String orderId);

    Optional<List<OrderEntity>> findAllByCreateDateBetween(Date startDate, Date endDate);

    Optional<List<OrderEntity>> findAllByCustomerId(String customerId);

    default Optional<List<OrderEntity>> findOrderListByDateInterval(Date startDate, Date endDate) {
        return findAllByCreateDateBetween(startDate, endDate);
    }
}
