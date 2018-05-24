package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.entity.CategoryExpense;
import com.test.entity.DateExpense;
import com.test.entity.Expense;
import com.test.entity.MonthExpense;
import com.test.entity.PaymodeExpense;
import com.test.entity.PaymodeMonthExpense;
import com.test.service.ExpenseService;

@Controller
@RequestMapping(path="expense")
public class ReportController {
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("allExpenses")
	public ResponseEntity<List<Expense>> getAllExpenses() {
		List<Expense> list = expenseService.getAllExpenses();
		return new ResponseEntity<List<Expense>>(list, HttpStatus.OK);
	}
	
	@GetMapping("totalExpense")
	public ResponseEntity<Double> getTotalExpense() {
		double obj = expenseService.getTotalExpenses();
		return new ResponseEntity<Double>(obj, HttpStatus.OK);
	}
	
	@GetMapping("categoryExpenses")
	public ResponseEntity<List<CategoryExpense>> getCategoryExpense() {
		List<CategoryExpense> list = expenseService.getCategoryExpenses();
		return new ResponseEntity<List<CategoryExpense>>(list, HttpStatus.OK);
	}
	
	@GetMapping("dateExpenses")
	public ResponseEntity<List<DateExpense>> getDateExpenses() {
		List<DateExpense> list = expenseService.getDateExpenses();
		return new ResponseEntity<List<DateExpense>>(list, HttpStatus.OK);
	}
	
	@GetMapping("monthExpenses")
	public ResponseEntity<List<MonthExpense>> getMonthExpenses() {
		List<MonthExpense> list = expenseService.getMonthExpenses();
		return new ResponseEntity<List<MonthExpense>>(list, HttpStatus.OK);
	}
	
	@GetMapping("paymodeExpenses")
	public ResponseEntity<List<PaymodeExpense>> getPaymodeExpenses() {
		List<PaymodeExpense> list = expenseService.getPaymodeExpenses();
		return new ResponseEntity<List<PaymodeExpense>>(list, HttpStatus.OK);
	}
	
	@GetMapping("paymodeMonthExpenses")
	public ResponseEntity<List<PaymodeMonthExpense>> getPaymodeMonthExpenses() {
		List<PaymodeMonthExpense> list = expenseService.getPaymodeMonthExpenses();
		return new ResponseEntity<List<PaymodeMonthExpense>>(list, HttpStatus.OK);
	}
}
