package com.epam.kiev.springdao.repository.job;

import java.util.List;

import com.epam.kiev.springdao.domain.job.Job;

public interface JobRepository {
	Job find(Integer id);
	List<Job> findAll();
	boolean create(Job employee);
	Job findByName(String name);
}
