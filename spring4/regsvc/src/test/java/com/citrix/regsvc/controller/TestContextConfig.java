package com.citrix.regsvc.controller;

import com.citrix.regsvc.config.ContextConfig;
import com.citrix.regsvc.data.registrant.RegistrantDataManager;
import com.citrix.regsvc.service.registrant.RegistrantServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: Andy Paladino
 * @version: 10/3/14
 */
@Configuration
@Import(ContextConfig.class)
public class TestContextConfig {

    @Autowired
    private RegistrantDataManager registrantDataManager;

    @Bean
    public RegistrantServiceImpl registrantService(){
        RegistrantServiceImpl svc = new RegistrantServiceImpl();
        svc.setRegistrantDataManager(registrantDataManager);
        return svc;
    }

    @Bean
    public RegistrantController registrantController(){
        RegistrantController registrantController = new RegistrantController();
        registrantController.setRegistrantService(registrantService());
        return registrantController;
    }
}
