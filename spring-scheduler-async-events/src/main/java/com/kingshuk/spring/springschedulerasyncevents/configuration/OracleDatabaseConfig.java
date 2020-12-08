package com.kingshuk.spring.springschedulerasyncevents.configuration;

import com.kingshuk.spring.springschedulerasyncevents.model.dao.EmployeeH2Repository;
import com.kingshuk.spring.springschedulerasyncevents.model.dao.EmployeeOracleRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration("oracleDatabaseConfig")
@EnableJpaRepositories(basePackages = {"com.kingshuk.spring.springschedulerasyncevents.model" },
		 entityManagerFactoryRef = "oracleEntityManager"
		, transactionManagerRef = "oracleTransactionManager",
		excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, value = {EmployeeOracleRepository.class,
				EmployeeH2Repository.class}))
@EnableTransactionManagement
@PropertySource(value = "classpath:database-oracle-dev.properties")
public class OracleDatabaseConfig extends DatabaseConfig {


	@Override
	@Bean(name = "oracleDataSource")
	@Qualifier("oracleDataSource")
	@Primary
	public DataSource createDataSource() {
		return DataSourceBuilder.create().driverClassName(environment.getProperty(ORACLE_DRIVER_CLASS_NAME_PROPERTY))
				.url(environment.getProperty(ORACLE_DATABASE_URL_PROPERTY))
				.username(environment.getProperty(ORACLE_DATABASE_USERNAME_PROPERTY))
				.password(environment.getProperty(ORACLE_DATABASE_CRED_PROPERTY)).build();
	}

	@Override
	@Bean(name = "oracleTransactionManager")
	@Qualifier("oracleTransactionManager")
	@Primary
	public PlatformTransactionManager createTransactionManager() {
		return new JpaTransactionManager(oracleEntityManagerFactoryBean().getObject());
	}

	@Bean(name = "oracleEntityManager")
	@Qualifier("oracleEntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = createEntityManagerFactoryBean();

		entityManagerFactoryBean.setJpaProperties(decorateOracleSpecificProperties());

		return entityManagerFactoryBean;
	}

	private Properties decorateOracleSpecificProperties() {
		Properties properties = decorateBaseDatabaseProperties();
		properties.setProperty(HIBERNATE_DDL2AUTO_ATTRIBUTE,
				environment.getProperty(ORACLE_HIBERNATE_DDL2AUTO_PROPERTY));
		properties.setProperty(HIBERNATE_DIALECT_ATTRIBUTE, environment.getProperty(ORACLE_HIBERNATE_DIALECT_PROPERTY));

		return properties;
	}

}
