package com.budgetbuddy.accountservice.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "accounts")
public class Account {
    
    @Id
    private String name;
    
    private Date lastseen;

    private List<Item> expenses;

    private List<Item> incomes;

    private Saving saving;
    
    private String note;
    
}
