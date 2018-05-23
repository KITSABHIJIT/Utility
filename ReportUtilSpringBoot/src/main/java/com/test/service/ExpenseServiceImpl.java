package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.ExpenseDaoImpl;
import com.test.entity.Expense;
@Service
public class ExpenseServiceImpl implements ExpenseService {
	@Autowired
	private ExpenseDaoImpl expenseDaoImpl;

	@Override
	public List<Expense> getAllExpenses() {
		return expenseDaoImpl.getAllExpenses();
	}
}
