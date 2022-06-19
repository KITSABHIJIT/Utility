package com.test.service;

import java.util.List;

import com.test.entity.CategoryExpense;
import com.test.entity.DateExpense;
import com.test.entity.Expense;
import com.test.entity.MonthExpense;
import com.test.entity.PaymodeExpense;
import com.test.entity.PaymodeMonthExpense;

public interface ExpenseService {
	List<Expense> getAllExpenses(String startDate,String endDate);
	double getTotalExpenses(String startDate,String endDate);
	List<CategoryExpense> getCategoryExpenses(String startDate,String endDate);
	List<DateExpense> getDateExpenses();
	List<MonthExpense> getMonthExpenses();
	List<PaymodeExpense> getPaymodeExpenses(String startDate,String endDate);
	List<PaymodeMonthExpense> getPaymodeMonthExpenses();
}
