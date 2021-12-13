package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.AuditEntity;
import com.getir.readingisgood.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Repository
public interface AuditRepository extends MongoRepository<AuditEntity, String> {
}
