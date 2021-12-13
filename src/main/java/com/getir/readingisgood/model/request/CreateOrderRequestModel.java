package com.getir.readingisgood.model.request;

import com.getir.readingisgood.entity.BookEntity;
import com.getir.readingisgood.validator.Positive;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
public class CreateOrderRequestModel {
    @NotNull
    private String customerId;
    @NotNull
    private String bookId;
    @NotNull
    @Positive
    private int purchasedBookCount;
}
