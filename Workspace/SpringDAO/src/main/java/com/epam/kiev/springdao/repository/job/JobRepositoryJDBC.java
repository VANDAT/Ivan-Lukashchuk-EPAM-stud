package com.epam.kiev.springdao.repository.job;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.kiev.springdao.domain.job.Job;

@Repository
public class JobRepositoryJDBC implements JobRepository {

	private JobRowMapper jobRowMapper;
	private JdbcTemplate jdbcTemplate;
		
	public void setJobRowMapper(JobRowMapper jobRowMapper) {
		this.jobRowMapper = jobRowMapper;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Job find(Integer id) {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM Job WHERE id=?", new Object[] { id },
				jobRowMapper);
	}

	@Override
	public List<Job> findAll() {
		return jdbcTemplate
				.query("SELECT * FROM Job", jobRowMapper);
	}

	@Override
	public boolean create(Job job) {
		return jdbcTemplate.update(
				"INSERT INTO Job (name) VALUES (?)",
				job.getId()) == 1;
	}

	@Override
	public Job findByName(String name) {
		return jdbcTemplate.queryForObject(
				"SELECT * FROM Job WHERE name=?",
				new Object[] { name }, jobRowMapper);
	}

}
