package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.entity.Expense;
import com.test.service.ExpenseService;

@Controller
@RequestMapping(path="expense")
public class ReportController {
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("allExpenses")
	public ResponseEntity<List<Expense>> getAllArticles() {
		List<Expense> list = expenseService.getAllExpenses();
		return new ResponseEntity<List<Expense>>(list, HttpStatus.OK);
	}
}
