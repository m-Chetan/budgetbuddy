package com.budgetbuddy.statisticsservice.service;

import java.util.List;

import com.budgetbuddy.statisticsservice.domain.Account;
import com.budgetbuddy.statisticsservice.domain.timeseries.DataPoint;

public interface StatisticsService {
    List<DataPoint> findByAccountName(String accountName);

    DataPoint save(String accountName, Account account);
}
