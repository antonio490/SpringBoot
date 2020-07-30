package com.antoniosanzc.spring.boot.token.example.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.antoniosanzc.spring.boot.token.example.authentication.rest.LoginValidator;

public class PropertyLoginValidator extends LoginValidator {

	private static final Logger log = LoggerFactory.getLogger(PropertyLoginValidator.class);
    private Map<String, String> users = new HashMap<>();
    
    @Autowired
    private DAO dao;

    @PostConstruct
    protected void demoInfo()
    {
        if (!users.isEmpty()) {
            log.info("Use any of the following Authorization headers to register:");
            for (String user : users.keySet()) {
                String pass = users.get(user);
                String header = new String(Base64.getEncoder().encode((user + ":" + pass).getBytes()));
                log.info("=> {}: Basic {}", user, header);
            }
        } else {
            log.warn("No users have been registered");
        }
    }

    
    @Override
    public boolean isAuthValid(String username, String pass) {
   	 	if(dao.UserCredentials(username, pass)) {
   	 		return true;
   	 	}else {
   	 		return false;
   	 	}

    }

    public Map<String, String> getUsers()
    {
        return users;
    }

    public void setUsers(Map<String, String> users)
    {
        this.users = users;
    }

}
