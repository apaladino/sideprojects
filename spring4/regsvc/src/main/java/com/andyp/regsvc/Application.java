package com.andyp.regsvc;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@EnableAutoConfiguration
@Configuration
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
