package com.test.practice.interview.programs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortListObject {
	public static class Employee{
		String name;
		int id;
		double salary;
		
		public Employee(String name,int id, double salary){
			this.name=name;
			this.id=id;
			this.salary=salary;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public double getSalary() {
			return salary;
		}
		public void setSalary(double salary) {
			this.salary = salary;
		}
		@Override
		public String toString() {
			return "Employee [name=" + name + ", id=" + id + ", salary="
					+ salary + "]";
		}
		
		
	}
	public static void main(String ... strings){
		List<Employee> empList=new ArrayList<Employee>();
		empList.add(new Employee("Emp1",4,6570.45));
		empList.add(new Employee("Emp2",8,4534.55));
		empList.add(new Employee("Emp3",3,696.35));
		empList.add(new Employee("Emp4",87,7676.87));
		Collections.sort(empList, new Comparator<Employee>(){
			@Override
			public int compare(Employee e1, Employee e2) {
				Double sal1=e1.getSalary();
				Double sal2=e2.getSalary();
				return sal2.compareTo(sal1);
			}
		});
		
		 for(Employee e: empList){
	            System.out.println(e.toString());
	        }
	}
}
