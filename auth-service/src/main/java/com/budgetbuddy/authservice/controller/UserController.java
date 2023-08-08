package com.budgetbuddy.authservice.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budgetbuddy.authservice.domain.User;
import com.budgetbuddy.authservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
	private UserService userService;

	@GetMapping("/current")
	public Principal getUser(Principal principal) {
		return principal;
	}

	@PreAuthorize("#oauth2.hasScope('server')")
	@PostMapping
	public void createUser(@RequestBody User user) {
		userService.create(user);
	}

}
