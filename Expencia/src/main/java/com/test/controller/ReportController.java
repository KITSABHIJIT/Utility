package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.test.entity.CategoryExpense;
import com.test.entity.DateExpense;
import com.test.entity.Expense;
import com.test.entity.MonthExpense;
import com.test.entity.PaymodeExpense;
import com.test.entity.PaymodeMonthExpense;
import com.test.service.ExpenseService;

@Controller
public class ReportController {
	@Autowired
	private ExpenseService expenseService;
	
	
	@GetMapping("/")
    public String main(Model model) {
        return "home"; //view
    }
	@GetMapping("/loadBankStatements")
    public String loadBankStatements(Model model) {
        return "loadBankStatements"; //view
    }
	@GetMapping("/categoryExpense")
    public String categoryExpense(Model model) {
        return "categoryExpense"; //view
    }
	@GetMapping("/dailyExpense")
    public String dailyExpense(Model model) {
        return "dailyExpense"; //view
    }
	@GetMapping("/expenseDetails")
    public String expenseDetails(Model model) {
        return "expenseDetails"; //view
    }
	@GetMapping("/monthExpense")
    public String monthExpense(Model model) {
        return "monthExpense"; //view
    }
	@GetMapping("/monthlyPaymodeExpense")
    public String monthlyPaymodeExpense(Model model) {
        return "monthlyPaymodeExpense"; //view
    }
	@GetMapping("/monthlyPaymodeStackedExpense")
    public String monthlyPaymodeStackedExpense(Model model) {
        return "monthlyPaymodeStackedExpense"; //view
    }
	@GetMapping("/paymodeExpense")
    public String paymodeExpense(Model model) {
        return "paymodeExpense"; //view
    }
	@GetMapping("/totalExpense")
    public String totalExpense(Model model) {
        return "totalExpense"; //view
    }
	
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
	@RequestMapping(value = "/loadBankStatements", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void loadBankStatements() {
		expenseService.loadBankStatements();
	}
}
