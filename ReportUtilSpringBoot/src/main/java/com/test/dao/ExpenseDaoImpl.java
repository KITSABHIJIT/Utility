package com.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.test.entity.CategoryExpense;
import com.test.entity.DateExpense;
import com.test.entity.Expense;
import com.test.entity.MonthExpense;
import com.test.entity.PaymodeExpense;
import com.test.entity.PaymodeMonthExpense;
@Repository
public class ExpenseDaoImpl implements ExpenseDao {
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public List<Expense> getAllExpenses() {
		String hql = "from Expense order by expenseDate";
		return (List<Expense>) entityManager.createQuery(hql).getResultList();
	}

	@Override
	public double getTotalExpenses() {
		String hql = "select sum(amount) as total_expense from Expense";
		return (Double) entityManager.createQuery(hql).getSingleResult();
	}

	@Override
	public List<CategoryExpense> getCategoryExpenses() {
		String hql = "from CategoryExpense";
		return (List<CategoryExpense>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public List<DateExpense> getDateExpenses() {
		String hql = "from DateExpense";
		return (List<DateExpense>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public List<MonthExpense> getMonthExpenses() {
		String hql = "from MonthExpense";
		return (List<MonthExpense>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public List<PaymodeExpense> getPaymodeExpenses() {
		String hql = "from PaymodeExpense";
		return (List<PaymodeExpense>) entityManager.createQuery(hql).getResultList();
	}
	
	@Override
	public List<PaymodeMonthExpense> getPaymodeMonthExpenses() {
		String hql = "from PaymodeMonthExpense";
		return (List<PaymodeMonthExpense>) entityManager.createQuery(hql).getResultList();
	}

}
