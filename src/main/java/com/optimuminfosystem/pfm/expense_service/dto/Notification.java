package com.optimuminfosystem.pfm.expense_service.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String category;
    private Double budgetAmount;
    private Double expenseAmount;
    private String expenseDescription;

    public Notification() {
    }

    public Notification(Long id, Long userId, String category, Double budgetAmount, Double expenseAmount, String expenseDescription) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.budgetAmount = budgetAmount;
        this.expenseAmount = expenseAmount;
        this.expenseDescription = expenseDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", userId=" + userId +
                ", category='" + category + '\'' +
                ", budgetAmount=" + budgetAmount +
                ", expenseAmount=" + expenseAmount +
                ", expenseDescription='" + expenseDescription + '\'' +
                '}';
    }

    public static NotificationBuilder builder() {
        return new NotificationBuilder();
    }

    public static class NotificationBuilder {
        private Long id;
        private Long userId;
        private String category;
        private Double budgetAmount;
        private Double expenseAmount;
        private String expenseDescription;

        public NotificationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public NotificationBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public NotificationBuilder category(String category) {
            this.category = category;
            return this;
        }

        public NotificationBuilder budgetAmount(Double budgetAmount) {
            this.budgetAmount = budgetAmount;
            return this;
        }

        public NotificationBuilder expenseAmount(Double expenseAmount) {
            this.expenseAmount = expenseAmount;
            return this;
        }

        public NotificationBuilder expenseDescription(String expenseDescription) {
            this.expenseDescription = expenseDescription;
            return this;
        }

        public Notification build() {
            return new Notification(id, userId, category, budgetAmount, expenseAmount, expenseDescription);
        }
    }


}
