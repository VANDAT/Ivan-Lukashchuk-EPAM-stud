package com.epam.kiev.springdao.repository.employee;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.kiev.springdao.domain.employee.Employee;
import com.epam.kiev.springdao.repository.DAOTestsTemplate;

public class EmployeeRepositoryJDBCTest extends DAOTestsTemplate {

	@Autowired
	private EmployeeRepository employeeRepository;

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

	@Test
	public void testCreate() {
		Employee employee = new Employee();
		employee.setName("TestEmp");
		employeeRepository.create(employee);
		int size = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Employee",
				Integer.class);
		Assert.assertEquals(1, size);
	}

}
