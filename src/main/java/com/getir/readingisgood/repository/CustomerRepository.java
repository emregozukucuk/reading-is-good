package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Repository
public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByEmail(String email);
    Optional<CustomerEntity> findByCustomerId(String customerId);
}
