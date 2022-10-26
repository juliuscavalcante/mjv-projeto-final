package com.mjv.mjvracingbackend.config;

import com.mjv.mjvracingbackend.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public void instantiateDB() {
        this.dbService.instantiateDB();
    }
}
