package com.test.dao;

import java.util.List;

import com.test.entity.CategoryExpense;
import com.test.entity.DateExpense;
import com.test.entity.Expense;
import com.test.entity.MonthExpense;
import com.test.entity.PaymodeExpense;
import com.test.entity.PaymodeMonthExpense;

public interface ExpenseDao {
	List<Expense> getAllExpenses();
	double getTotalExpenses();
	List<CategoryExpense> getCategoryExpenses();
	List<DateExpense> getDateExpenses();
	List<MonthExpense> getMonthExpenses();
	List<PaymodeExpense> getPaymodeExpenses();
	List<PaymodeMonthExpense> getPaymodeMonthExpenses();
}
