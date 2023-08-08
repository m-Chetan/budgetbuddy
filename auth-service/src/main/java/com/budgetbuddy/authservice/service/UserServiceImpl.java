package com.budgetbuddy.authservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.budgetbuddy.authservice.domain.User;
import com.budgetbuddy.authservice.repository.UserRepository;

public class UserServiceImpl implements UserService{

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
	private UserRepository repository;


	@Override
	public void create(User user) {
		Optional<User> existing = repository.findById(user.getUsername());
		existing.ifPresent(it-> {throw new IllegalArgumentException("user already exists: " + it.getUsername());});

		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);

		repository.save(user);
		
	}
    
}
