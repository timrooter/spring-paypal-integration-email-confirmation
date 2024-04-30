package com.dteam.springboottasks.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActualDomainConfig {
    @Value("${spring.application.actual-domain}")
    private String ACTUAL_DOMAIN;

    @Bean
    public String getACTUAL_DOMAIN(){
        return ACTUAL_DOMAIN;
    }
}
