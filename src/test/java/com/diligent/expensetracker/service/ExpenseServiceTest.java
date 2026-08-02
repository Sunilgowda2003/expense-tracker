package com.diligent.expensetracker.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.diligent.expensetracker.exception.ExpenseNotFoundException;
import com.diligent.expensetracker.model.Expense;

class ExpenseServiceTest {

    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        expenseService = new ExpenseService();
    }

    private Expense createExpense(String title,
                                  double amount,
                                  String category) {

        Expense expense = new Expense();
        expense.setTitle(title);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDate(LocalDate.now());

        return expense;
    }

    @Test
    void testAddExpense() {

        Expense expense =
                createExpense("Groceries",2500,"Food");

        Expense saved =
                expenseService.addExpense(expense);

        assertNotNull(saved.getId());
        assertEquals(1,
                expenseService.getAllExpenses().size());
    }

    @Test
    void testTotalExpenses() {

        expenseService.addExpense(
                createExpense("Food",1000,"Food"));

        expenseService.addExpense(
                createExpense("Travel",2000,"Travel"));

        assertEquals(3000,
                expenseService.getTotalExpenses());
    }

    @Test
    void testCategoryFilter() {

        expenseService.addExpense(
                createExpense("Pizza",500,"Food"));

        expenseService.addExpense(
                createExpense("Bus",100,"Travel"));

        assertEquals(1,
                expenseService.getExpensesByCategory("Food").size());
    }

    @Test
    void testDeleteExpense() {

        Expense expense =
                expenseService.addExpense(
                        createExpense("Movie",300,"Entertainment"));

        expenseService.deleteExpense(expense.getId());

        assertEquals(0,
                expenseService.getAllExpenses().size());
    }

    @Test
    void testDeleteExpenseNotFound() {

        assertThrows(
                ExpenseNotFoundException.class,
                () -> expenseService.deleteExpense(100L));
    }
}