package com.antoniosanzc.spring.boot.token.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.antoniosanzc.spring.boot.token.example.keys.Rsa;

@Repository
public class Dao extends JdbcDaoSupport {
	
	@Autowired
	public Dao(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	private List<Clase> claseList;
	private static final Logger log = LoggerFactory.getLogger(Dao.class);
	private List<User> users = new ArrayList<User>();
	private Rsa rsa = new Rsa();


	public List<Clase> claseList(){
		String query = "";
		
		
		try {
			
			synchronized (this) {	
				claseList = getJdbcTemplate().query(query, new MapperClass());
				log.info("Running query: ", query);
			}
		}catch(DataAccessException e) {
			
			log.error("Error running query: ", e);
		}
			
		return claseList;
	}
	
	public boolean UserCredentials(String username, String password) {
		
		String sql = "";
		
		Object[] filtros = new Object[] {username};
		
		try {
			log.info("running query: " + sql);
			
			users = getJdbcTemplate().query(sql, filtros, new MapperUser());
			
			for (int i = 0; i < users.size(); i++) {
				
				String pass = String.valueOf(users.get(i).getPasssword());
				password = rsa.encrypt(password);
				
				log.info("username: " + username + " password: " + password);
				log.info("usuario bbdd: " + users.get(i).getUsername() + " password: " + pass);
				
				if(password.equals(pass)) {
					log.info("correct auth password");
					return true;
				}
				
			}
					
		}catch(DataAccessException e) {
			log.error("Error running query: " + e);
		}
		log.info("Incorrect user authorization");
		return false;
	}

	public List<Clase> claseListById(int id) {
		String query = "";
		
		try {
			
			synchronized (this) {	
				claseList = getJdbcTemplate().query(query, new MapperClass());
				log.info("Running query: ", query);
			}
		}catch(DataAccessException e) {
			
			log.error("Error running query: ", e);
		}
			
		return claseList;
	}

}