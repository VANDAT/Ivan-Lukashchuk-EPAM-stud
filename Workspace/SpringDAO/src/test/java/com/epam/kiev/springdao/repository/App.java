package com.epam.kiev.springdao.repository;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.kiev.springdao.domain.employee.Employee;
import com.epam.kiev.springdao.repository.employee.EmployeeRepository;

public class App {
	
	public static void main(String[] args) {
	EmployeeRepository employeeRepository = new ClassPathXmlApplicationContext(
				"persistenceContextTest.xml").getBean("employeeRepository",
				EmployeeRepository.class);
	Employee employee = new Employee();
	employee.setName("asd");
	System.out.println(employeeRepository.create(employee));
	System.out.println(employeeRepository.findAll());
	System.out.println(1);
	}
}
