package com.budgetbuddy.accountservice.domain;

public enum Currency {
    INR, USD;
    public static Currency getDefault() {
		return INR;
	}
}
