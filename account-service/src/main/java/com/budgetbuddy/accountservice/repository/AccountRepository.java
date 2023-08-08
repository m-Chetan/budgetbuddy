package com.budgetbuddy.accountservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.budgetbuddy.accountservice.domain.Account;

public interface AccountRepository extends MongoRepository<Account,String>{
    Account findByName(String name);
}
