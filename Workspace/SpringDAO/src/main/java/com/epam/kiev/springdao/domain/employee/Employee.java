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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", job=");
		builder.append(job);
		builder.append("]");
		return builder.toString();
	}
	 
	 
}
