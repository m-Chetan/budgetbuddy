package com.budgetbuddy.statisticsservice.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "accounts")
public class Account {
    
    private List<Item> expenses;

    private List<Item> incomes;

    private Saving saving;
    
}
