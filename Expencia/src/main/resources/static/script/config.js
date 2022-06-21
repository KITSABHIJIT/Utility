var globalUrl="localhost";
var port=":8080";
var baseUrl="";
var protocol="http://";
var categoryExpenseUrl=protocol+globalUrl+port+baseUrl+"/categoryExpenses/";
var dailyExpenseUrl=protocol+globalUrl+port+baseUrl+"/dateExpenses/";
var expenseDetailsUrl=protocol+globalUrl+port+baseUrl+"/allExpenses/";
var monthExpenseUrl=protocol+globalUrl+port+baseUrl+"/monthExpenses/";
var monthlyPaymodeExpenseUrl=protocol+globalUrl+port+baseUrl+"/paymodeMonthExpenses/";
var paymodeExpenseUrl=protocol+globalUrl+port+baseUrl+"/paymodeExpenses/";
var totalExpenseUrl=protocol+globalUrl+port+baseUrl+"/totalExpense/";
if (moment() === null || moment() !== undefined){
var start = moment().subtract(12, 'month').startOf('month');
var end = moment();
var datePickerConfiguredRange= {
           'Today': [moment(), moment()],
           'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
           'Last 7 Days': [moment().subtract(6, 'days'), moment()],
           'Last 30 Days': [moment().subtract(29, 'days'), moment()],
           'This Month': [moment().startOf('month'), moment().endOf('month')],
           'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last 3 Months': [moment().subtract(3, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
		   'Last Year': [moment().subtract(12, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        };
        }