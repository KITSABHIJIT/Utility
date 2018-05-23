package com.test.dao;

import java.util.List;

import com.test.entity.Expense;

public interface ExpenseDao {
	 List<Expense> getAllExpenses();
}
