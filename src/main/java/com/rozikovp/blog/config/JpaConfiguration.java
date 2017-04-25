package com.rozikovp.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.core.env.Environment;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ParvizR on 25/04/2017.
 *
 * @author ParvizR
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.rozikovp.blog.domain.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableTransactionManagement
public class JpaConfiguration {


    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.jpa.show-sql}")
    private String showSql;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String hbm2ddl;


    @Autowired
    private Environment environment;

    public JpaConfiguration(){
//        driver = environment.getProperty("spring.datasource.driver-class-name");
//        url = environment.getProperty("spring.datasource.url");
//        username = environment.getProperty("spring.datasource.username");
//        password = environment.getProperty("spring.datasource.password");
//        showSql = environment.getProperty("spring.jpa.show-sql");
//        hibernateDialect = environment.getProperty("spring.jpa.properties.hibernate.dialect");
//        hbm2ddl = environment.getProperty("spring.jpa.hibernate.ddl-auto");
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();

//        properties.put("hibernate.show_sql", showSql);
//        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
//        properties.put("hibernate.connection.url", url);
//        properties.put("hibernate.connection.driver_class", driver);
//        properties.put("hibernate.connection.username", username);
//        properties.put("hibernate.connection.password", password);

        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan(new String[]{"com.rozikovp.blog"});
        factoryBean.setDataSource(dataSource());
        factoryBean.setJpaProperties(jpaProperties());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        return factoryBean;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(Boolean.parseBoolean(showSql));
        jpaVendorAdapter.setDatabasePlatform(hibernateDialect);
        return jpaVendorAdapter;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
