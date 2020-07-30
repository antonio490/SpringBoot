package com.antoniosanzc.spring.boot.token.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

public class MapperUser implements RowMapper<User> {

	private static final Logger log = LoggerFactory.getLogger(Dao.class);

	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		String username = rs.getString(1);
		String password = rs.getString(2);

		User user = new User(username, password);
		
		return user;
	}
}
