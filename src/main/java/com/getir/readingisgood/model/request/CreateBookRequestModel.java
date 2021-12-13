package com.getir.readingisgood.model.request;

import com.getir.readingisgood.validator.Positive;
import com.getir.readingisgood.validator.PositiveBigDecimal;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
public class CreateBookRequestModel {
    @NotNull
    private String bookName;
    @NotNull
    @PositiveBigDecimal
    private BigDecimal price;
    @NotNull
    @Positive
    private Integer stockCount;
}
