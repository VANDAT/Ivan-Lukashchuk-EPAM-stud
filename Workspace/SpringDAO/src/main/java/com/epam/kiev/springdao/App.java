package com.epam.kiev.springdao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.kiev.springdao.repository.employee.EmployeeRepository;

public class App {
	
	public static void main(String[] args) {
	EmployeeRepository employeeRepository = new ClassPathXmlApplicationContext(
				"persistenceContextTest.xml").getBean("employeeRepository",
				EmployeeRepository.class);
	System.out.println(1);

	}

}
