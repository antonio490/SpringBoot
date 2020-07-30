package com.antoniosanzc.spring.boot.token.example.authentication.rest;

public class SessionResponse {

    private String sessionKey;

    public String getSessionKey()
    {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey)
    {
        this.sessionKey = sessionKey;
    }
}
