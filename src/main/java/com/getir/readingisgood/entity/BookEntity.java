package com.getir.readingisgood.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
@Document(collection = "Book")
public class BookEntity {

    @Id
    private String bookId;
    private String bookName;
    private BigDecimal price;
    private Integer stockCount;
    private Date createDate;
    private Date lastUpdateDate;
}
