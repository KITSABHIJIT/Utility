package com.test.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.test.entity.Expense;
@Repository
public class ExpenseDaoImpl implements ExpenseDao {
	@PersistenceContext	
	private EntityManager entityManager;
	@Override
	public List<Expense> getAllExpenses() {
		String hql = "from Expense order by expenseDate";
		return (List<Expense>) entityManager.createQuery(hql).getResultList();
	}

}
