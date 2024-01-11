package com.springboot.mongo.api.services;

import com.springboot.mongo.api.entity.Expense;
import com.springboot.mongo.api.entity.ExpenseCategory;
import com.springboot.mongo.api.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense addExpense(Expense expense) {
        return expenseRepository.insert(expense);
    }

    public Expense updateExpense(@RequestBody Expense expense) {
        Expense expenseDetail = expenseRepository.findById(Integer.valueOf(expense.getId()))
                .orElseThrow(() -> new RuntimeException(String.format("Can't Find Expense Id : %s ", expense.getId())));
        expenseDetail.setExpenseName(expense.getExpenseName());
        expenseDetail.setExpenseCategory(expense.getExpenseCategory());
        expenseDetail.setExpenseAmount(expense.getExpenseAmount());
        expenseRepository.save(expenseDetail);
        return expenseDetail;
    }

    public Expense getExpenseByName(String name) {
        return expenseRepository.getExpenseByName(name)
                .orElseThrow(() -> new RuntimeException(String.format("Can't find the ExpenseName %s", name)));
    }

    public void deleteExpense(String id) {
        expenseRepository.deleteById(Integer.valueOf(id));
    }

}
