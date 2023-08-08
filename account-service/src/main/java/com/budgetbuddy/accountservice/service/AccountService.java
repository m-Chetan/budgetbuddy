package com.budgetbuddy.accountservice.service;

import com.budgetbuddy.accountservice.domain.Account;
import com.budgetbuddy.accountservice.domain.User;

public interface AccountService {
    Account findByName(String accountName);
    Account create(User user);
    void saveChanges(String name, Account update);
}
