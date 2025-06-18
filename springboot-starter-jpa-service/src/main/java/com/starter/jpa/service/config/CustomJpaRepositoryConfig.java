package com.starter.jpa.service.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**	If want to provide custom configuration to JPA Repositories,
 * 	we can utilize this class (Java based configuration)
 */

/**
 * 	The preceding configuration class sets up an embedded H2 database by using the 
 * 	EmbeddedDatabaseBuilder API of spring-jdbc.
 * 	Spring Data then sets up an EntityManagerFactory and uses Hibernate as the sample 
 * 	persistence provider.
 * 	The last infrastructure component declared here is the JpaTransactionManager.
 * 	Finally, the example activates Spring Data JPA repositories by using the 
 * 	@EnableJpaRepositories annotation, which essentially carries the same attributes 
 * 	as the XML namespace.
 * 
 * 	If no base package is configured, it uses the one in which the configuration 
 * 	class resides
 */

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
@Profile("custom-jpa")
public class CustomJpaRepositoryConfig {

	  @Bean
	  public DataSource dataSource() {

	    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	    return builder.setType(EmbeddedDatabaseType.H2).build();
	  }

	  
	  /**	We use LocalContainerEntityManagerFactoryBean instead of EntityManagerFactory 
	   * 	Because it also participates in exception translation mechanisms in addition 
	   * 	to creating EntityManagerFactory
	   */
	  @Bean
	  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

	    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    vendorAdapter.setGenerateDdl(true);

	    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
	    factory.setJpaVendorAdapter(vendorAdapter);
	    
	    /** Specify Entity packages, to avoid persistence.xml */
	    factory.setPackagesToScan("com.starter.jpa.service.beans","com.*");
	    factory.setDataSource(dataSource());
	    return factory;
	  }

	  @Bean
	  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

	    JpaTransactionManager txManager = new JpaTransactionManager();
	    txManager.setEntityManagerFactory(entityManagerFactory);
	    return txManager;
	  }
}
