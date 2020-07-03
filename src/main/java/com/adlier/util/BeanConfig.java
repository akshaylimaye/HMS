
package com.adlier.util;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class BeanConfig {


	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.adlier.*");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		// SQL
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://akshayLimaye;user=sa;password=abc@1234;database=HostpitalManagementDb");
		dataSource.setUsername("sa");
		dataSource.setPassword("abc@1234");

<<<<<<< HEAD
		
=======
		// MYSQL
		/*
		 * dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		 * dataSource.setUrl("jdbc:mysql://localhost:3306/HMSdb");
		 * dataSource.setUsername("root"); dataSource.setPassword("abcd@123");
		 */

>>>>>>> 8efd47f99813311d01b8fea112bfff22b7be44e5
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
		hibernateProperties.setProperty("showSql", "true");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		return hibernateProperties;
	}
}
