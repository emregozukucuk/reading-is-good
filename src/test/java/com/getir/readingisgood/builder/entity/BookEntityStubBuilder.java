package com.getir.readingisgood.builder.entity;

import com.getir.readingisgood.entity.BookEntity;

import java.math.BigDecimal;
import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
public class BookEntityStubBuilder {

    public static BookEntity create(){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId("1111");
        bookEntity.setStockCount(10);
        bookEntity.setPrice(BigDecimal.valueOf(50.0));
        bookEntity.setBookName("Tehlikeli Oyunlar");
        bookEntity.setCreateDate(new Date());
        bookEntity.setLastUpdateDate(new Date());
        return bookEntity;
    }
}
