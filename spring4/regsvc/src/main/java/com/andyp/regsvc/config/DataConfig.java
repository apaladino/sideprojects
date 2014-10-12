package com.andyp.regsvc.config;

import java.util.Properties;
import javax.sql.DataSource;

import com.andyp.regsvc.data.registrant.RegistrantDataManagerImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: Andy Paladino
 * @version: 10/3/14
 */
@Configuration
@EnableTransactionManagement
public class DataConfig {

    @Value("${db.driver}")
    private String dbDriver;

    @Value("${db.dbUrl}")
    private String dbUrl;

    @Value("${db.dbUsername}")
    private String dbUsername;

    @Value("${db.dbPassword}")
    private String dbPassword;

    @Value("${db.dialect}")
    private String dbDialect;

    @Value("${db.connection.pool_size}")
    private String dbConnectionPoolSize;

    @Value("${db.show_sql}")
    private String showSql;

    @Value("${db.hbm2ddl.auto}")
    private String hbm2ddl;


    @Bean
    public RegistrantDataManagerImpl registrantDataManager(){
        RegistrantDataManagerImpl registrantDataManager = new RegistrantDataManagerImpl();
        return registrantDataManager;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        System.out.println("####\r\r"  +System.getProperty("java.class.path"));
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        // sessionFactoryBean.setMappingResources("com/andyp/regsvc/domain/Registrant.hbm.xml-DELETEME");
        sessionFactoryBean.setPackagesToScan(new String[] { "com.andyp.regsvc.domain" });

        Properties properties = new Properties();
        properties.setProperty("connection.driver", "org.hsqldb.jdbcDriver");
        properties.setProperty("connection.url", dbUrl);
        properties.setProperty("connection.username", dbUsername);
        properties.setProperty("connection.password", dbPassword);
        properties.setProperty("dialect", dbDialect);
        properties.setProperty("connection.pool_size", dbConnectionPoolSize);
        properties.setProperty("show_sql", showSql);
        properties.setProperty("hbm2ddl.auto", hbm2ddl);
        sessionFactoryBean.setHibernateProperties(properties);

        return sessionFactoryBean;
    }

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
