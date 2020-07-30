package com.antoniosanzc.spring.boot.token.example.authentication.rest;

public abstract class LoginValidator {

    public abstract boolean isAuthValid (String user, String pass);

}