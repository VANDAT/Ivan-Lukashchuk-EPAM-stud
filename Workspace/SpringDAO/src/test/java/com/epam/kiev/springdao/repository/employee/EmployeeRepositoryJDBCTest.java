package com.epam.kiev.springdao.repository.employee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.kiev.springdao.domain.employee.Employee;
import com.epam.kiev.springdao.repository.DAOTestsTemplate;

public class EmployeeRepositoryJDBCTest extends DAOTestsTemplate {

	private EmployeeRepository employeeRepository = new ClassPathXmlApplicationContext(
			"persistenceContextTest.xml").getBean("employeeRepository",
			EmployeeRepository.class);

	public EmployeeRepositoryJDBCTest() {
	}

	@Before
	public void setUp() {
		jdbcTemplate.execute("TRUNCATE TABLE Employee");
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testCreateNoExceptions() {
		Employee employee = new Employee();
		employee.setName("TestEmp");
		employeeRepository.create(employee);
	}

}
