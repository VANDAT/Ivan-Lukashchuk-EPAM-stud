package com.epam.kiev.springdao.domain.employee;

import com.epam.kiev.springdao.domain.job.Job;

public class Employee {
	private Integer id;
	private String name;
	private Job job;
	 
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	 
	 
}
