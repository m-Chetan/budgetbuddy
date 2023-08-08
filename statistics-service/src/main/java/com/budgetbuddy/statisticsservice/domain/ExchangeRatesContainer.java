package com.budgetbuddy.statisticsservice.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import lombok.Data;

@Data
public class ExchangeRatesContainer {
    private LocalDate date = LocalDate.now();

	private Currency base;

	private Map<String, BigDecimal> rates;
}
