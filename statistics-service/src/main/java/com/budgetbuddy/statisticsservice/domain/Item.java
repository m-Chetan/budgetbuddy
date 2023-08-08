package com.budgetbuddy.statisticsservice.domain;

import java.math.BigDecimal;
import java.util.Currency;

import lombok.Data;

@Data
public class Item {

	private String title;

	private BigDecimal amount;

	private Currency currency;

	private TimePeriod period;

}
