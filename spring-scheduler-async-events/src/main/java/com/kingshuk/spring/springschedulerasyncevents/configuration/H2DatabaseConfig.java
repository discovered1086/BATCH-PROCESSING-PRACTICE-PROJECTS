package com.kingshuk.spring.springschedulerasyncevents.configuration;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.kingshuk.spring.springschedulerasyncevents.model"},
        entityManagerFactoryRef = "h2EntityManager"
        , transactionManagerRef = "h2TransactionManager")
@EnableTransactionManagement
@PropertySource(value = "classpath:database-h2-test.properties")
public class H2DatabaseConfig extends DatabaseConfig {

    @Bean
    public ServletRegistrationBean<WebServlet> h2ServletRegistration() {
        ServletRegistrationBean<WebServlet> theServlet = new ServletRegistrationBean<>(new WebServlet());
        theServlet.addUrlMappings("/h2-console/*");
        theServlet.addInitParameter("webAllowOthers", "true");

        return theServlet;
    }

    @Override
    @Bean(name = "h2DataSource")
    @Qualifier("h2DataSource")
    public DataSource createDataSource() {
        return DataSourceBuilder.create().driverClassName(environment.getProperty(H2_DRIVER_CLASS_NAME_PROPERTY))
                .url(environment.getProperty(H2_DATABASE_URL_PROPERTY))
                .username(environment.getProperty(H2_DATABASE_USERNAME_PROPERTY))
                .password(environment.getProperty(H2_DATABASE_CRED_PROPERTY)).build();
    }

    @Override
    @Bean(name = "h2TransactionManager")
    @Qualifier("h2TransactionManager")
    public PlatformTransactionManager createTransactionManager() {
        return new JpaTransactionManager(h2EntityManagerFactoryBean().getObject());
    }

    @Bean(name = "h2EntityManager")
    @Qualifier("h2EntityManager")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = createEntityManagerFactoryBean();

        entityManagerFactoryBean.setJpaProperties(decorateH2SpecificDatabaseProperties());

        return entityManagerFactoryBean;
    }

    private Properties decorateH2SpecificDatabaseProperties() {
        Properties properties = decorateBaseDatabaseProperties();
        properties.setProperty(HIBERNATE_DDL2AUTO_ATTRIBUTE, environment.getProperty(H2_HIBERNATE_DDL2AUTO_PROPERTY));
        properties.setProperty(HIBERNATE_DIALECT_ATTRIBUTE, environment.getProperty(H2_HIBERNATE_DIALECT_PROPERTY));

        return properties;
    }

}
