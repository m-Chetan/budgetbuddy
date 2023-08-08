package com.budgetbuddy.statisticsservice.client;

import java.util.Collections;

import org.springframework.stereotype.Component;

import com.budgetbuddy.statisticsservice.domain.Currency;
import com.budgetbuddy.statisticsservice.domain.ExchangeRatesContainer;

@Component
public class ExchangeRatesClientFallback implements ExchangeRatesClient{
    @Override
    public ExchangeRatesContainer getRates(Currency base) {
        ExchangeRatesContainer container = new ExchangeRatesContainer();
        container.setBase(Currency.getBase());
        container.setRates(Collections.emptyMap());
        return container;
    }
}
