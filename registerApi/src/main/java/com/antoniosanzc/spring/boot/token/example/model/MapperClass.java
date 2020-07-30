package com.antoniosanzc.spring.boot.token.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperClass implements RowMapper<Clase>{

	public Clase mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		int id = rs.getInt(1);
		
		Clase clase = new Clase();
		return clase;
		
	}

}
