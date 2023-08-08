package com.budgetbuddy.statisticsservice.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.budgetbuddy.statisticsservice.domain.Account;
import com.budgetbuddy.statisticsservice.domain.timeseries.DataPoint;
import com.budgetbuddy.statisticsservice.service.StatisticsService;

@RestController
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/current")
    public List<DataPoint> getCurrentAccountStatistics(Principal principal){
        return statisticsService.findByAccountName(principal.getName());
    }

    @GetMapping("/{accountName}")
    public List<DataPoint> getStatisticsByAccountName(@PathVariable String accountName) {
		return statisticsService.findByAccountName(accountName);
	}

    @PutMapping("/{accountName}")
    public void saveAccountStatistics(@PathVariable String accountName,@RequestBody Account account) {
		statisticsService.save(accountName, account);
	}
}
