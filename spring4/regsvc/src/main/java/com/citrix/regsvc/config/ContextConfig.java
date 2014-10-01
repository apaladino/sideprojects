package com.citrix.regsvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Created by apaladino on 9/29/14.
 */
@Configuration
@ImportResource("classpath:/com/citrix/regsvc/regsvc-context.xml")
public class ContextConfig {

    @Value("${jdbc.dbUrl}")
    private String dbUrl;

    @Value("${jdbc.dbUsername}")
    private String dbUsername;

    @Value("${jdbc.dbPassword}")
    private String dbPassword;

    /*@Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){

        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        return configurer;
    }*/

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
    
    @Bean
    public DataSource dataSource(){

       return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
            .addScript("classpath:db/create-tables.sql")
            .build();

    }
}
