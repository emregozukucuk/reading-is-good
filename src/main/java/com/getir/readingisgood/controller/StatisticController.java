package com.getir.readingisgood.controller;

import com.getir.readingisgood.model.response.MonthlyStatisticResponseModel;
import com.getir.readingisgood.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/***
 Created on 2021

 @author emre.gozukucuk
 **/
@RestController
@RequestMapping("/api/v1/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/monthly/{customerId}")
    public ResponseEntity<List<MonthlyStatisticResponseModel>> getMonthlyStatistic(@PathVariable String customerId){
        return ResponseEntity.ok(statisticService.getMonthlyStatistic(customerId));
    }


}
