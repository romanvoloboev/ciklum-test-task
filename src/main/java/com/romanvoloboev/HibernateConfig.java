package com.romanvoloboev;

import com.romanvoloboev.dao.CollectionsDAOImpl;
import com.romanvoloboev.dao.HibernateDAOImpl;
import com.romanvoloboev.dao.MessageDAO;
import com.romanvoloboev.util.CollectionsStoragePropertyCondition;
import com.romanvoloboev.util.DatabaseStoragePropertyCondition;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author romanvoloboev
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class HibernateConfig {

    private final Environment env;

    @Autowired
    public HibernateConfig(Environment env) {
        this.env = env;
    }

    @Bean
    @Conditional(DatabaseStoragePropertyCondition.class)
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.romanvoloboev.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(AvailableSettings.DIALECT, env.getProperty("hibernate.dialect"));
        properties.put(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.show_sql"));
        properties.put(AvailableSettings.GLOBALLY_QUOTED_IDENTIFIERS, env.getProperty("hibernate.globally_quoted_identifiers"));
        properties.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }

    @Bean
    @Autowired
    @Conditional(DatabaseStoragePropertyCondition.class)
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(s);
        return transactionManager;
    }

    @Bean(name = "messageDAO")
    @Conditional(CollectionsStoragePropertyCondition.class)
    public MessageDAO collectionsDaoImpl() {
        return new CollectionsDAOImpl();
    }

    @Bean(name = "messageDAO")
    @Conditional(DatabaseStoragePropertyCondition.class)
    public MessageDAO hibernateDaoImpl(SessionFactory s) {
        return new HibernateDAOImpl(s);
    }
}
