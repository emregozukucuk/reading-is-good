package com.getir.readingisgood.repository;

import com.getir.readingisgood.entity.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Repository
public interface BookRepository extends MongoRepository<BookEntity, String> {
    Optional<BookEntity> findByBookId(String bookId);

}
