package com.optimuminfosystem.pfm.expense_service.service;

import com.optimuminfosystem.pfm.expense_service.dto.Budget;
import com.optimuminfosystem.pfm.expense_service.dto.Notification;
import com.optimuminfosystem.pfm.expense_service.model.Expense;
import com.optimuminfosystem.pfm.expense_service.repository.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpenseServiceTest {

    private ExpenseRepository expenseRepository;
    private RestTemplate restTemplate;
    private ExpenseService expenseService;

    private Expense expense;

    @BeforeEach
    void setUp() {
        expenseRepository = mock(ExpenseRepository.class);
        restTemplate = mock(RestTemplate.class);
        expenseService = new ExpenseService(expenseRepository, restTemplate);

        expense = new Expense();
        expense.setId(1L);
        expense.setUserId(1L);
        expense.setCategory("Groceries");
        expense.setDescription("Weekly shopping");
        expense.setAmount(1200.0);
    }

    @Test
    void testCreateExpense_WithNotification() {
        Budget budget = new Budget();
        budget.setUserId(1L);
        budget.setCategory("Groceries");
        budget.setAmount(1000.0);

        when(restTemplate.getForObject(anyString(), eq(Budget.class))).thenReturn(budget);
        when(expenseRepository.save(any(Expense.class))).thenReturn(expense);

        Expense saved = expenseService.createExpense(expense);

        assertNotNull(saved);
        assertEquals(1L, saved.getId());

        ArgumentCaptor<Notification> notificationCaptor = ArgumentCaptor.forClass(Notification.class);
        verify(restTemplate).postForObject(eq("http://localhost:8083/notifications"), notificationCaptor.capture(), eq(Notification.class));

        Notification sent = notificationCaptor.getValue();
        assertEquals("Groceries", sent.getCategory());
        assertEquals(1200.0, sent.getExpenseAmount());
    }

    @Test
    void testCreateExpense_WithoutNotification() {
        Budget budget = new Budget();
        budget.setUserId(1L);
        budget.setCategory("Groceries");
        budget.setAmount(1500.0); // expense < budget

        when(restTemplate.getForObject(anyString(), eq(Budget.class))).thenReturn(budget);
        when(expenseRepository.save(any(Expense.class))).thenReturn(expense);

        Expense saved = expenseService.createExpense(expense);

        assertNotNull(saved);
        verify(restTemplate, never()).postForObject(eq("http://localhost:8083/notifications"), any(), eq(Notification.class));
    }

    @Test
    void testGetExpensesByUserId() {
        when(expenseRepository.findByUserId(1L)).thenReturn(Arrays.asList(expense));

        List<Expense> expenses = expenseService.getExpensesByUserId(1L);
        assertEquals(1, expenses.size());
        assertEquals("Groceries", expenses.get(0).getCategory());
    }
}