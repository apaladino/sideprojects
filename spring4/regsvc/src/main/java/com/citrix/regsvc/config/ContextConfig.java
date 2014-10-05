package com.citrix.regsvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by apaladino on 9/29/14.
 */
@Configuration
@ImportResource("classpath:/com/citrix/regsvc/regsvc-context.xml")
@Import(DataConfig.class)
@ComponentScan
public class ContextConfig {


    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        return new MappingJackson2HttpMessageConverter();
    }
    //org.springframework.http.converter.json.MappingJacksonHttpMessageConverter
}
