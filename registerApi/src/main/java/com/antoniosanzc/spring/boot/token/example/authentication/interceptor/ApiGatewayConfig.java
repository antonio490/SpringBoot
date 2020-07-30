package com.antoniosanzc.spring.boot.token.example.authentication.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
@ComponentScan
@EnableScheduling
public class ApiGatewayConfig {

	private static final Logger log = LoggerFactory.getLogger(ApiGatewayConfig.class);

    @Autowired(required = false)
    private ApiGatewayInterceptor apiGatewayInterceptor;

    public void addInterceptors(InterceptorRegistry registry)
    {
        if (apiGatewayInterceptor != null) {
            log.info("Registered security gateway");
            registry.addInterceptor(apiGatewayInterceptor);
        } else {
            log.info("Api gateway has been disabled");
        }

    }
}
