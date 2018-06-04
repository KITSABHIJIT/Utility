package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.ExpenseDaoImpl;
import com.test.entity.CategoryExpense;
import com.test.entity.DateExpense;
import com.test.entity.Expense;
import com.test.entity.MonthExpense;
import com.test.entity.PaymodeExpense;
import com.test.entity.PaymodeMonthExpense;
@Service
public class ExpenseServiceImpl implements ExpenseService {
	@Autowired
	private ExpenseDaoImpl expenseDaoImpl;

	@Override
	public List<Expense> getAllExpenses(String startDate,String endDate) {
		return expenseDaoImpl.getAllExpenses(startDate,endDate);
	}

	@Override
	public double getTotalExpenses(String startDate,String endDate) {
		return expenseDaoImpl.getTotalExpenses(startDate,endDate);
	}

	@Override
	public List<CategoryExpense> getCategoryExpenses(String startDate,String endDate) {
		return expenseDaoImpl.getCategoryExpenses(startDate,endDate);
	}

	@Override
	public List<DateExpense> getDateExpenses() {
		return expenseDaoImpl.getDateExpenses();
	}

	@Override
	public List<MonthExpense> getMonthExpenses() {
		return expenseDaoImpl.getMonthExpenses();
	}

	@Override
	public List<PaymodeExpense> getPaymodeExpenses(String startDate,String endDate) {
		return expenseDaoImpl.getPaymodeExpenses(startDate,endDate);
	}

	@Override
	public List<PaymodeMonthExpense> getPaymodeMonthExpenses() {
		return expenseDaoImpl.getPaymodeMonthExpenses();
	}
}
