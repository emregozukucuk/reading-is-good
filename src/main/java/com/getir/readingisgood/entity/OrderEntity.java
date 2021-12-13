package com.getir.readingisgood.entity;

import com.getir.readingisgood.enums.StatusType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
@Document(collection = "Order")
public class OrderEntity {
    @Id
    private String orderId;
    private String customerId;
    private String bookId;
    private Integer purchasedBookCount;
    private StatusType statusType;
    private Date createDate;
    private Date lastUpdateDate;
}
