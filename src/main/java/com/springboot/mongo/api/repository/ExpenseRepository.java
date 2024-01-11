package com.springboot.mongo.api.repository;

import com.springboot.mongo.api.entity.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ExpenseRepository extends MongoRepository<Expense,Integer> {
    @Query("{'name':?0}")
    Optional<Expense> getExpenseByName(String name);
}
