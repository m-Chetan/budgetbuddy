package com.budgetbuddy.authservice.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.budgetbuddy.authservice.repository.UserRepository;

public class MongoUserDetailsService implements UserDetailsService{

     @Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return repository.findById(username).orElseThrow(()->new UsernameNotFoundException(username));
	}
    
}
