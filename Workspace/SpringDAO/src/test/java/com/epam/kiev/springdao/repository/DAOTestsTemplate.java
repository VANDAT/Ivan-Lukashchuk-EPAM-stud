package com.epam.kiev.springdao.repository;

import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
public abstract class DAOTestsTemplate {

    protected JdbcTemplate jdbcTemplate = new ClassPathXmlApplicationContext(
			"persistenceContextTest.xml").getBean("jdbcTemplate",
					JdbcTemplate.class);; 

}
