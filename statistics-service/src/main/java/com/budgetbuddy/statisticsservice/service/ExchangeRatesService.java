package com.budgetbuddy.statisticsservice.service;

import java.math.BigDecimal;
import java.util.Map;

import com.budgetbuddy.statisticsservice.domain.Currency;

public interface ExchangeRatesService {
    Map<Currency, BigDecimal> getCurrentRates();

	BigDecimal convert(Currency from, Currency to, BigDecimal amount);
}
