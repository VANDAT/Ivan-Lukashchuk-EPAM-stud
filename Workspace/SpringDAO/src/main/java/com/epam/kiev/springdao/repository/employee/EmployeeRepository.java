package com.epam.kiev.springdao.repository.employee;

import java.util.List;

import com.epam.kiev.springdao.domain.employee.Employee;
import com.epam.kiev.springdao.domain.job.Job;

public interface EmployeeRepository {
	Employee find(Integer id);
	List<Employee> findAll();
	boolean update(Employee employee);
	boolean create(Employee employee);
	Employee findByName(String name);
	List<Employee> findByJob(Job job);
}
