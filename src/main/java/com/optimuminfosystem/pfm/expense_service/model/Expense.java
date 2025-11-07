package com.optimuminfosystem.pfm.expense_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Expense {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String category;
    private String description;
    private Double amount;

    public Expense() {
    }

    public Expense(Long id, Long userId, String category, String description, Double amount) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.description = description;
        this.amount = amount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", userId=" + userId +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }

    public static ExpenseBuilder builder() {
        return new ExpenseBuilder();
    }

    public static class ExpenseBuilder {
        private Long id;
        private Long userId;
        private String category;
        private String description;
        private Double amount;

        public ExpenseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ExpenseBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public ExpenseBuilder category(String category) {
            this.category = category;
            return this;
        }

        public ExpenseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ExpenseBuilder amount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Expense build() {
            return new Expense(id, userId, category, description, amount);
        }
    }
}
