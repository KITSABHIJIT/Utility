package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value = "/allExpenses/{startDate}/{endDate}")
	@ResponseBody
	public ResponseEntity<List<Expense>> getAllExpenses(@PathVariable String startDate, @PathVariable String endDate) {
		List<Expense> list = expenseService.getAllExpenses(startDate,endDate);
		return new ResponseEntity<List<Expense>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/totalExpense/{startDate}/{endDate}")
	@ResponseBody
	public ResponseEntity<Double> getTotalExpense(@PathVariable String startDate, @PathVariable String endDate) {
		double obj = expenseService.getTotalExpenses(startDate,endDate);
		return new ResponseEntity<Double>(obj, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/categoryExpenses/{startDate}/{endDate}")
	@ResponseBody
	public ResponseEntity<List<CategoryExpense>> getCategoryExpense(@PathVariable String startDate, @PathVariable String endDate) {
		List<CategoryExpense> list = expenseService.getCategoryExpenses(startDate,endDate);
		return new ResponseEntity<List<CategoryExpense>>(list, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/paymodeExpenses/{startDate}/{endDate}")
	@ResponseBody
	public ResponseEntity<List<PaymodeExpense>> getPaymodeExpenses(@PathVariable String startDate, @PathVariable String endDate) {
		List<PaymodeExpense> list = expenseService.getPaymodeExpenses(startDate,endDate);
		return new ResponseEntity<List<PaymodeExpense>>(list, HttpStatus.OK);
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
	
	@GetMapping("paymodeMonthExpenses")
	public ResponseEntity<List<PaymodeMonthExpense>> getPaymodeMonthExpenses() {
		List<PaymodeMonthExpense> list = expenseService.getPaymodeMonthExpenses();
		return new ResponseEntity<List<PaymodeMonthExpense>>(list, HttpStatus.OK);
	}
}
