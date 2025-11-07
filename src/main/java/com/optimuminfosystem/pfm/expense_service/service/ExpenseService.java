package com.optimuminfosystem.pfm.expense_service.service;

import com.optimuminfosystem.pfm.expense_service.dto.Budget;
import com.optimuminfosystem.pfm.expense_service.dto.Notification;
import com.optimuminfosystem.pfm.expense_service.model.Expense;
import com.optimuminfosystem.pfm.expense_service.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private static final Logger log = LoggerFactory.getLogger(ExpenseService.class);
    private final RestTemplate restTemplate;

    public ExpenseService(ExpenseRepository expenseRepository, RestTemplate restTemplate) {
        this.expenseRepository = expenseRepository;
        this.restTemplate = restTemplate;
    }

    private final String BUDGET_SERVICE_URL = "http://localhost:8081/budgets";
    private final String NOTIFICATION_SERVICE_URL = "http://localhost:8083/notifications";

    /**
     * Creates a new expense and checks if it exceeds the budget.
     * If exceeded, sends a notification via Notification Service.
     *
     * @param expense Expense object containing userId, category, description, and amount
     * @return Saved Expense object
     */
    public Expense createExpense(Expense expense) {
        log.info("Creating expense: {}", expense);
        Budget budget = restTemplate.getForObject(
                BUDGET_SERVICE_URL + "/" + expense.getUserId() + "/" + expense.getCategory(),
                Budget.class
        );
        log.info("Fetched budget: {}", budget);

        if (budget != null && expense.getAmount() > budget.getAmount()) {
            Notification notification = new Notification();
            notification.setUserId(expense.getUserId());
            notification.setCategory(expense.getCategory());
            notification.setBudgetAmount(budget.getAmount());
            notification.setExpenseAmount(expense.getAmount());
            notification.setExpenseDescription(expense.getDescription());


            restTemplate.postForObject(NOTIFICATION_SERVICE_URL, notification, Notification.class);
            log.info("Notification sent: {}", notification);
        }
        Expense saved = expenseRepository.save(expense);
        log.info("Expense saved successfully: {}", saved);
        return saved;
    }

    /**
     * Creates a new expense and checks if it exceeds the budget.
     * If exceeded, sends a notification via Notification Service.
     *
     * @param expense Expense object containing userId, category, description, and amount
     * @return Saved Expense object
     */
    public List<Expense> getExpensesByUserId(Long userId) {
        log.info("Fetching expenses for userId: {}", userId);
        return expenseRepository.findByUserId(userId);
    }
}
