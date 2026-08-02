package com.diligent.expensetracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.diligent.expensetracker.exception.ExpenseNotFoundException;
import com.diligent.expensetracker.model.Expense;

@Service
public class ExpenseService {

    // In-memory storage
    private final List<Expense> expenseList = new ArrayList<>();

    // Auto-generated ID
    private Long nextId = 1L;

    // Add Expense
    public Expense addExpense(Expense expense) {

        expense.setId(nextId++);
        expenseList.add(expense);

        return expense;
    }

    // Get All Expenses
    public List<Expense> getAllExpenses() {

        return new ArrayList<>(expenseList);
    }

    // Filter Expenses by Category
    public List<Expense> getExpensesByCategory(String category) {

        return expenseList.stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Calculate Total Expenses
    public Double getTotalExpenses() {

        return expenseList.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // Calculate Total Expenses by Category
    public Double getTotalExpensesByCategory(String category) {

        return expenseList.stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    // Delete Expense
    public String deleteExpense(Long id) {

        Expense expense = expenseList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new ExpenseNotFoundException("Expense with ID " + id + " not found."));

        expenseList.remove(expense);

        return "Expense deleted successfully.";
    }

}