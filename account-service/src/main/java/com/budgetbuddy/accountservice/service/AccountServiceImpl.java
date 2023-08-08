package com.budgetbuddy.accountservice.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budgetbuddy.accountservice.domain.Account;
import com.budgetbuddy.accountservice.domain.Currency;
import com.budgetbuddy.accountservice.domain.Saving;
import com.budgetbuddy.accountservice.domain.User;
import com.budgetbuddy.accountservice.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository repository;

	@Override
	public Account findByName(String accountName) {
		return repository.findByName(accountName);
	}

	@Override
	public Account create(User user) {
        Saving saving = new Saving();
        saving.setAmount(new BigDecimal(0));
		saving.setCurrency(Currency.getDefault());
		saving.setInterest(new BigDecimal(0));
		saving.setDeposit(false);
		saving.setCapitalization(false);
        
        Account account = new Account();
        
        account.setName(user.getUsername());
        account.setLastseen(new Date());
        account.setSaving(saving);

        repository.save(account);

		return account;
	}

	@Override
	public void saveChanges(String name, Account update) {
		
	}
    
}
