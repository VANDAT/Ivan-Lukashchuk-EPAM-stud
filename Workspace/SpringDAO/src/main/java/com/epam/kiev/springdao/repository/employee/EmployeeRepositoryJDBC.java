package com.epam.kiev.springdao.repository.employee;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.kiev.springdao.domain.employee.Employee;
import com.epam.kiev.springdao.domain.job.Job;

@Repository
public class EmployeeRepositoryJDBC implements EmployeeRepository {
	
	private EmployeeRowMapper employeeRowMapper;
	private JdbcTemplate jdbcTemplate;
		
	public void setEmployeeRowMapper(EmployeeRowMapper employeeRowMapper) {
		this.employeeRowMapper = employeeRowMapper;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Employee find(Integer id) {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM Employee WHERE id=?", new Object[] { id },
				employeeRowMapper);
	}

	@Override
	public List<Employee> findAll() {
		return jdbcTemplate
				.query("SELECT * FROM Employee", employeeRowMapper);
	}

	@Override
	public boolean create(Employee employee) {
		Integer jobId = null;
		if (employee.getJob() != null){
			jobId = employee.getJob().getId();
		}
		return jdbcTemplate.update(
				"INSERT INTO Employee (name, job_id) VALUES (?,?)",
				employee.getName(), jobId) == 1;
	}

	@Override
	public Employee findByName(String name) {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM Employee WHERE name=?",
				new Object[] { name }, employeeRowMapper);
	}

	@Override
	public List<Employee> findByJob(Job job) {
		return jdbcTemplate.query("SELECT * FROM Employee WHERE job_id=?",
				new Object[] { job.getId() }, employeeRowMapper);
	}

	@Override
	public boolean update(Employee employee) {
		return jdbcTemplate
				.update("UPDATE Employee SET name = ? job_id = ? WHERE id = ?",
						employee.getName(), employee.getJob().getId(),
						employee.getId()) == 1;
	}

}
