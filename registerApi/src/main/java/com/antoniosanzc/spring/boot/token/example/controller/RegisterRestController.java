package com.antoniosanzc.spring.boot.token.example.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antoniosanzc.spring.boot.token.example.authentication.core.ApiGatewaySettings;
import com.antoniosanzc.spring.boot.token.example.authentication.core.SessionManager;
import com.antoniosanzc.spring.boot.token.example.authentication.rest.LoginValidator;
import com.antoniosanzc.spring.boot.token.example.authentication.rest.SessionResponse;

@RestController
@RequestMapping("${app.authenticationMapping:/register}")
@ConditionalOnProperty(value = "app.registerEnabled", havingValue = "true")
public class RegisterRestController {

	 private static final Logger log = LoggerFactory.getLogger(RegisterRestController.class);

	    @Autowired
	    private SessionManager sessionManager;
	    @Autowired
	    private ApiGatewaySettings apiGatewaySettings;
	    @Autowired(required = false)
	    private LoginValidator loginValidator;
	    
	    @PostMapping
	    public SessionResponse login (HttpServletRequest request, HttpServletResponse response) throws IOException
	    {
	        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
	        log.info("Authorization header: '{}'", auth);
	        if (StringUtils.isEmpty(auth) || !auth.toLowerCase().startsWith("basic ")) {
	            log.info("Bad header");
	        } else {
	            String [] credentials = new String(Base64.getDecoder().decode(auth.substring(6))).split(":");
	            if (credentials.length != 2) {
	                log.info("Bad credentials found");
	            }
	            else {
	                String user = credentials[0];
	                String pass = credentials[1];


	                if(loginValidator == null || loginValidator.isAuthValid(user, pass)) {
	                	log.info("session user: " + user);

	                	 SessionResponse authResponse = new SessionResponse();
		                 authResponse.setSessionKey(sessionManager.generateSession(user));
		                 return authResponse;
	                }else {
	                	log.info("Invalid user/password");
	                }
	                
	            }
	        }

	        response.setHeader(HttpHeaders.WWW_AUTHENTICATE, apiGatewaySettings.getAuthenticationEndpoint());
	        response.sendError(HttpStatus.UNAUTHORIZED.value());
	        return null;
	    }
}