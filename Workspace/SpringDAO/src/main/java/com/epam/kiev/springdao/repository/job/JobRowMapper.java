package com.epam.kiev.springdao.repository.job;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.kiev.springdao.domain.job.Job;

public class JobRowMapper implements RowMapper<Job> {

	@Override
	public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
		Job job = new Job();
		job.setId(rs.getInt("id"));
		job.setName(rs.getString("name"));
		return job;
	}

}
