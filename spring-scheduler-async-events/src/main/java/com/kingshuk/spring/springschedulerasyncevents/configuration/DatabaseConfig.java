package com.kingshuk.spring.springschedulerasyncevents.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public abstract class DatabaseConfig {

	public static final String HIBERNATE_SHOW_SQL_ATTRIBUTE = "hibernate.show_sql";
	public static final String HIBERNATE_FORMAT_SQL_ATTRIBUTE = "hibernate.format_sql";
	public static final String HIBERNATE_GENERATE_STATISTICS_ATTRIBUTE = "hibernate.generate_staticstics";
	public static final String JPA_QUERY_TIMEOUT_ATTRIBUTE = "javax.persistance.query.timeout";
	public static final String HIBERNATE_USE_SECOND_LEVEL_CACHE_ATTRIBUTE = "hibernate.cache.use_second_level_cache";
	public static final String HIBERNATE_DDL2AUTO_ATTRIBUTE = "hibernate.hbm2ddl.auto";
	public static final String HIBERNATE_DIALECT_ATTRIBUTE = "hibernate.dialect";
	public static final String HIBERNATE_TIMEZONE_ATTRIBUTE = "hibernate.jdbc.time_zone";

	public static final String H2_DRIVER_CLASS_NAME_PROPERTY = "datasource.h2.driverClassName";
	public static final String ORACLE_DRIVER_CLASS_NAME_PROPERTY = "datasource.oracle.driverClassName";

	public static final String H2_DATABASE_URL_PROPERTY = "datasource.h2.url";
	public static final String ORACLE_DATABASE_URL_PROPERTY = "datasource.oracle.url";

	public static final String H2_DATABASE_USERNAME_PROPERTY = "datasource.h2.username";
	public static final String ORACLE_DATABASE_USERNAME_PROPERTY = "datasource.oracle.username";

	public static final String H2_DATABASE_CRED_PROPERTY = "datasource.h2.password";
	public static final String ORACLE_DATABASE_CRED_PROPERTY = "datasource.oracle.password";

	public static final String H2_HIBERNATE_DDL2AUTO_PROPERTY = "datasource.h2.hibernate.hbm2ddl.auto";
	public static final String ORACLE_HIBERNATE_DDL2AUTO_PROPERTY = "datasource.oracle.hibernate.hbm2ddl.auto";

	public static final String H2_HIBERNATE_DIALECT_PROPERTY = "datasource.h2.hibernate.dialect";
	public static final String ORACLE_HIBERNATE_DIALECT_PROPERTY = "datasource.oracle.hibernate.dialect";

	protected static final String[] BASE_JPA_REPOSITORIES_PACKAGE = {
			"com.kingshuk.spring.springschedulerasyncevents.model" };
	
	@Autowired 
	protected Environment environment;


	public abstract DataSource createDataSource();

	public abstract PlatformTransactionManager createTransactionManager();

	public LocalContainerEntityManagerFactoryBean createEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPackagesToScan(BASE_JPA_REPOSITORIES_PACKAGE);

		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		entityManagerFactoryBean.setDataSource(createDataSource());

		return entityManagerFactoryBean;
	}

	@Bean(name = "databaseProperties")
	public Properties decorateBaseDatabaseProperties() {
		Properties properties = new Properties();
		properties.setProperty(HIBERNATE_SHOW_SQL_ATTRIBUTE, environment.getProperty(HIBERNATE_SHOW_SQL_ATTRIBUTE));

		properties.setProperty(HIBERNATE_FORMAT_SQL_ATTRIBUTE, environment.getProperty(HIBERNATE_FORMAT_SQL_ATTRIBUTE));

		properties.setProperty(HIBERNATE_GENERATE_STATISTICS_ATTRIBUTE,
				environment.getProperty(HIBERNATE_GENERATE_STATISTICS_ATTRIBUTE));

		properties.setProperty(JPA_QUERY_TIMEOUT_ATTRIBUTE, environment.getProperty(JPA_QUERY_TIMEOUT_ATTRIBUTE));
		properties.setProperty(HIBERNATE_TIMEZONE_ATTRIBUTE, environment.getProperty(HIBERNATE_TIMEZONE_ATTRIBUTE));

		return properties;
	}



}
