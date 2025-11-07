package com.optimuminfosystem.pfm.expense_service.controller;

import com.optimuminfosystem.pfm.expense_service.dto.Budget;
import com.optimuminfosystem.pfm.expense_service.dto.Notification;
import com.optimuminfosystem.pfm.expense_service.model.Expense;
import com.optimuminfosystem.pfm.expense_service.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);

    public ExpenseController(ExpenseService expenseService, RestTemplate restTemplate) {
        this.expenseService = expenseService;
        this.restTemplate = restTemplate;
    }

    private final String BUDGET_SERVICE_URL = "http://localhost:8081/budgets";
    private final String NOTIFICATION_SERVICE_URL = "http://localhost:8083/notifications";

    /**
     * Creates a new expense. Checks against budget and sends notification if exceeded.
     *
     * @param expense Expense object containing userId, category, description, and amount
     * @return The saved Expense object
     */
    @PostMapping
    public Expense createExpense(@RequestBody Expense expense) {
        log.info("Creating expense: {}", expense);
        return expenseService.createExpense(expense);
    }

    /**
     * Retrieves all expenses for a specific user.
     *
     * @param userId User ID
     * @return List of Expense objects
     */
    @GetMapping("/{userId}")
    public List<Expense> getExpenses(@PathVariable Long userId) {
        log.info("Fetching expenses for userId: {}", userId);
        return expenseService.getExpensesByUserId(userId);
    }
}
