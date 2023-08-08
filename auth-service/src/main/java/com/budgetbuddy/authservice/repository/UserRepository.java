package com.budgetbuddy.authservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.budgetbuddy.authservice.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>{
    
}
