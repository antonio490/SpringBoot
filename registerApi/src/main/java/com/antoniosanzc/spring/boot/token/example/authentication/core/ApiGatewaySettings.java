package com.antoniosanzc.spring.boot.token.example.authentication.core;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class ApiGatewaySettings {

	private long tokenTimeToLiveInSeconds = 300;
    private String keyAlgorithm = "AES";
    private String secret = "B^#vdaD$ZEM8j<>";
    private String authenticationEndpoint = "http://localhost:8080/spring-boot-token-example/register";
    private List<String> publicEndpoints = new ArrayList<>();

    public long getTokenTimeToLiveInSeconds()
    {
        return tokenTimeToLiveInSeconds;
    }

    public void setTokenTimeToLiveInSeconds(long tokenTimeToLiveInSeconds)
    {
        this.tokenTimeToLiveInSeconds = tokenTimeToLiveInSeconds;
    }

    public String getKeyAlgorithm()
    {
        return keyAlgorithm;
    }

    public void setKeyAlgorithm(String keyAlgorithm)
    {
        this.keyAlgorithm = keyAlgorithm;
    }

    public String getSecret()
    {
        return secret;
    }

    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    public String getAuthenticationEndpoint()
    {
        return authenticationEndpoint;
    }

    public void setAuthenticationEndpoint(String authenticationEndpoint)
    {
        this.authenticationEndpoint = authenticationEndpoint;
    }

    public List<String> getPublicEndpoints()
    {
        return publicEndpoints;
    }

    public void setPublicEndpoints(List<String> publicEndpoints)
    {
        this.publicEndpoints = publicEndpoints;
    }
}
