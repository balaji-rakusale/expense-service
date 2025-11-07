package com.optimuminfosystem.pfm.expense_service.repository;

import com.optimuminfosystem.pfm.expense_service.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);

}
