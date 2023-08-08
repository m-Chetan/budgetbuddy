package com.budgetbuddy.statisticsservice.domain;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Saving {

    private BigDecimal amount;

	private Currency currency;

	private BigDecimal interest;

	private Boolean deposit;

	private Boolean capitalization;

}
