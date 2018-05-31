package com.test.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	public List<Expense> getAllExpenses(String startDate,String endDate) {
		String hql = "select expenseDate,merchant,placeOfExpense,category,paymode,amount from Expense where expenseDate >= :startDate and expenseDate <= :endDate";
		return (List<Expense>) entityManager.createQuery(hql).setParameter("startDate", getDate(startDate)).setParameter("endDate", getDate(endDate)).getResultList();
	}

	@Override
	public double getTotalExpenses(String startDate,String endDate) {
		String hql = "select case when sum(amount) is null then 0.00 else sum(amount) end as total_expense from Expense where expenseDate >= :startDate and expenseDate <= :endDate";
		return (Double) entityManager.createQuery(hql).setParameter("startDate", getDate(startDate)).setParameter("endDate", getDate(endDate)).getSingleResult();
	}

	@Override
	public List<CategoryExpense> getCategoryExpenses(String startDate,String endDate) {
		String hql = "select category ,sum(amount) as amount from CategoryExpense where expenseDate >= :startDate and expenseDate <= :endDate group by category";
		List<Object[]> results=entityManager.createQuery(hql).setParameter("startDate", getDate(startDate)).setParameter("endDate", getDate(endDate)).getResultList();
		List<CategoryExpense> categoryExpenses=new ArrayList<CategoryExpense>();
		for(Object[] obj:results) {
			CategoryExpense categoryExpense=new CategoryExpense();
			categoryExpense.setAmount((Double)obj[1]);
			categoryExpense.setCategory((String)obj[0]);
			categoryExpenses.add(categoryExpense);
		}
		return categoryExpenses;
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
	public List<PaymodeExpense> getPaymodeExpenses(String startDate,String endDate) {
		String hql = "select sum(amount) as amount,paymode from PaymodeExpense where expenseDate >= :startDate and expenseDate <= :endDate group by paymode";
		List<Object[]> results=entityManager.createQuery(hql).setParameter("startDate", getDate(startDate)).setParameter("endDate", getDate(endDate)).getResultList();
		List<PaymodeExpense> paymodeExpenses=new ArrayList<PaymodeExpense>();
		for(Object[] obj:results) {
			PaymodeExpense paymodeExpense=new PaymodeExpense();
			paymodeExpense.setAmount((Double)obj[0]);
			paymodeExpense.setPaymode((String)obj[1]);
			paymodeExpenses.add(paymodeExpense);
		}
		return paymodeExpenses;
	}
	
	@Override
	public List<PaymodeMonthExpense> getPaymodeMonthExpenses() {
		String hql = "from PaymodeMonthExpense";
		return (List<PaymodeMonthExpense>) entityManager.createQuery(hql).getResultList();
	}
	
	private Date getDate(String date){
		DateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
		try {
			return format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
