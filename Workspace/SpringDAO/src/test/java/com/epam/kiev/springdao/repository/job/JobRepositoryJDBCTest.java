package com.epam.kiev.springdao.repository.job;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.kiev.springdao.domain.employee.Employee;
import com.epam.kiev.springdao.domain.job.Job;
import com.epam.kiev.springdao.repository.DAOTestsTemplate;

public class JobRepositoryJDBCTest extends DAOTestsTemplate {

	@Autowired
	private JobRepository jobRepository;

	public JobRepositoryJDBCTest() {
	}

	@Before
	public void setUp() {
		jdbcTemplate.execute("TRUNCATE TABLE Job");
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testCreateNoExceptions() {
		Job job = new Job();
		job.setName("TestEmp");
		jobRepository.create(job);
	}
	
	@Test
	public void testCreate() {
		Job job = new Job();
		job.setName("TestEmp");
		jobRepository.create(job);
		int size = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Job",
				Integer.class);
		Assert.assertEquals(1, size);
	}

}
