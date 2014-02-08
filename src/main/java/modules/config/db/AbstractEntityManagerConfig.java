package modules.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * User: lameroot
 * Date: 30.01.13
 * Time: 22:00
 */
public abstract class AbstractEntityManagerConfig {

    @Resource
    protected DataSource dataSource;
    @Resource
    protected Environment environment;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter());
        entityManagerFactory.setPackagesToScan(getPackagesToScan());
        entityManagerFactory.setJpaProperties(jpaProperties());
        entityManagerFactory.afterPropertiesSet();

        useEntityManagerBean(entityManagerFactory);
        return entityManagerFactory.getObject();
    }

    /**
     * If should use native entityManagerFactory
     * @param entityManagerFactoryBean
     */
    public void useEntityManagerBean(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {}

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslator exceptionTranslation() {
        return new HibernateExceptionTranslator();
    }

    public abstract JpaVendorAdapter vendorAdapter();
    public abstract Properties jpaProperties();

    /**
     * Массив сущностей, которе будут маппится с помощью хибернэйта
     * @return
     */
    public abstract String[] getPackagesToScan();

}
