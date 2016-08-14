package com.apaladino.peoplesearch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.apaladino.peoplesearch.config.DataConfig;

@Configuration
@ImportResource("classpath:/com/apaladino/peoplesearch/peoplesearch-context.xml")
@Import(DataConfig.class)
@ComponentScan
public class ContextConfig {

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        return new MappingJackson2HttpMessageConverter();
    }

}