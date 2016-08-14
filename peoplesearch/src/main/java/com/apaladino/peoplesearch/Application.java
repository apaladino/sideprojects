package com.apaladino.peoplesearch;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main Spring Application
 * 
 * @author Andy.Paladino
 * @version 8/14/2016
 */
@ComponentScan
@EnableAutoConfiguration
@Configuration
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}

