package com.getir.readingisgood.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@Getter
@Setter
public class OrderByDateIntervalRequestModel {
        @NotNull
        private Date startDate;
        @NotNull
        private Date endDate;
}
