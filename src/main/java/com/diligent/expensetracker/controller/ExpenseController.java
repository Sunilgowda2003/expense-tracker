package com.diligent.expensetracker.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.diligent.expensetracker.model.Expense;
import com.diligent.expensetracker.service.ExpenseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    // Constructor Injection
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // Add Expense
    @PostMapping
    public ResponseEntity<Expense> addExpense(@Valid @RequestBody Expense expense) {

        Expense savedExpense = expenseService.addExpense(expense);

        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    // View All Expenses
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {

        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    // Filter Expenses by Category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable String category) {

        return ResponseEntity.ok(expenseService.getExpensesByCategory(category));
    }

    // Calculate Total Expenses
    @GetMapping("/total")
    public ResponseEntity<Double> getTotalExpenses() {

        return ResponseEntity.ok(expenseService.getTotalExpenses());
    }

    // Calculate Total Expenses by Category
    @GetMapping("/total/{category}")
    public ResponseEntity<Double> getTotalExpensesByCategory(@PathVariable String category) {

        return ResponseEntity.ok(expenseService.getTotalExpensesByCategory(category));
    }

    // Delete Expense
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {

        return ResponseEntity.ok(expenseService.deleteExpense(id));
    }

}