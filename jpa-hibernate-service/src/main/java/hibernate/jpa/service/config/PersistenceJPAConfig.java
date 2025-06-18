package hibernate.jpa.service.config;
/*
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Configuration
//@EnableTransactionManagement	//enables annotation-driven transaction management
//@Order(2)
public class PersistenceJPAConfig {

	@Autowired
	private OracleConfig config;
	
	@Bean
	public DataSource dataSource() {
	    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(config.getDriver());
	    dataSource.setUrl(config.getUrl());
	    dataSource.setUsername(config.getUser());
	    dataSource.setPassword(config.getPass());
	    return dataSource;
	}
	
    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	    final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	    entityManagerFactoryBean.setDataSource(dataSource());
	    entityManagerFactoryBean.setPackagesToScan(new String[] {
	        "spring.jpa.service.beans"
	    });
	
	    final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
	    entityManagerFactoryBean.setJpaProperties(additionalProperties());
	
	    return entityManagerFactoryBean;
	}
	
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
	 
	private final Properties additionalProperties() {
		
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", config.getHbm2ddlAuto());
		hibernateProperties.setProperty("hibernate.dialect", config.getDialect());
		hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", config.getSecondLevelCache());
		hibernateProperties.setProperty("hibernate.cache.use_query_cache", config.getQueryCache());
		//hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
	 	return hibernateProperties;
	}
}
*/