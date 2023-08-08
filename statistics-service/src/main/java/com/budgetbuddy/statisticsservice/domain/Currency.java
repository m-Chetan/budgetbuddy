package com.budgetbuddy.statisticsservice.domain;

public enum Currency {
    INR, USD;
    public static Currency getBase() {
		  return INR;
	  }
}
