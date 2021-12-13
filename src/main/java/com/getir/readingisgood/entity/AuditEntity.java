package com.getir.readingisgood.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
@Document(collection = "Log")
public class AuditEntity {
    @Id
    private String logId;
    private String customerEmail;
    private String operation;
    private String message;
    private Date createDate;
}
