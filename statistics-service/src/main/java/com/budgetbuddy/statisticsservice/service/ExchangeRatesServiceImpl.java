package com.budgetbuddy.statisticsservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.budgetbuddy.statisticsservice.client.ExchangeRatesClient;
import com.budgetbuddy.statisticsservice.domain.Currency;
import com.budgetbuddy.statisticsservice.domain.ExchangeRatesContainer;
import com.google.common.collect.ImmutableMap;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService{

    private ExchangeRatesContainer container;

	@Autowired
	private ExchangeRatesClient client;

	@Override
	public Map<Currency, BigDecimal> getCurrentRates() {

		if (container == null || !container.getDate().equals(LocalDate.now())) {
			container = client.getRates(Currency.getBase());
			
		}

		return ImmutableMap.of(
				Currency.INR, container.getRates().get(Currency.INR.name()),
				Currency.USD, BigDecimal.ONE
		);
	}

	@Override
	public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {

		Map<Currency, BigDecimal> rates = getCurrentRates();
		BigDecimal ratio = rates.get(to).divide(rates.get(from), 4, RoundingMode.HALF_UP);

		return amount.multiply(ratio);
	}
    
}
