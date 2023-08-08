package com.budgetbuddy.statisticsservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.budgetbuddy.statisticsservice.domain.Currency;
import com.budgetbuddy.statisticsservice.domain.ExchangeRatesContainer;

@FeignClient(url = "${rates.url}", name = "rates-client", fallback = ExchangeRatesClientFallback.class)
public interface ExchangeRatesClient {

    @GetMapping("/latest")
    ExchangeRatesContainer getRates(@RequestParam("base") Currency base);

}