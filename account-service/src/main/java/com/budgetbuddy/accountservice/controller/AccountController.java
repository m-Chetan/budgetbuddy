package com.budgetbuddy.accountservice.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budgetbuddy.accountservice.domain.Account;
import com.budgetbuddy.accountservice.domain.User;
import com.budgetbuddy.accountservice.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @GetMapping("/{name}")
    public Account getAccountByName(@PathVariable String name){
        return accountService.findByName(name);
    }

    @GetMapping("/current")
    public Account getCurrentAccount(Principal principal){
        return accountService.findByName(principal.getName());
    }

    @PutMapping("/current")
    public void saveCurrentAccount(Principal principal, @RequestBody Account account) {
		accountService.saveChanges(principal.getName(), account);
	}

    @PostMapping("/")
    public Account createAccount(@RequestBody User user){
        return accountService.create(user);
    }
}
