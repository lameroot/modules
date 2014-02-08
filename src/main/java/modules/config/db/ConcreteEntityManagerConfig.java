package modules.config.db;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * User: lameroot
 * Date: 02.02.13
 * Time: 11:47
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public abstract class ConcreteEntityManagerConfig extends AbstractEntityManagerConfig {

    private SessionFactory sessionFactory;

    public static enum HBM2DDLAuto {
        UPDATE("update"),
        VALIDATE("validate"),
        CREATE("create"),
        CREATE_DROP("create-drop"),
        NOTHING("");

        private final String value;

        private HBM2DDLAuto(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @Bean
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", "false");
        properties.put("hibernate.jdbc.batch_size", 100);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto());
        properties.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
        properties.put("hibernate.cache.use_second_level_cache", "true");
        properties.put("hibernate.cache.use_query_cache", "true");
        properties.put("hibernate.connection.CharSet","utf8");
        properties.put("hibernate.connection.characterEncoding","utf8");
        properties.put("hibernate.connection.useUnicode","true");
        return properties;
    }

    /**
     * Return default adapter with next parameters: <br/>
     * showSql = general.hibernate.show_sql
     * dialect = general.hibernate.dialect
     * @see modules.config.db.mysql.MysqlEntityManagerConfig
     * @return
     */
    @Override
    @Bean
    public JpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(showSql());
        vendorAdapter.setDatabasePlatform(hibernateDialect());
        return vendorAdapter;
    }

    public boolean showSql() {
        Boolean showSql = environment.getProperty("general.hibernate.show_sql",Boolean.class);
        if ( null != showSql ) return showSql;
        return false;
    }

    /**
     * Have to return hibernate dialect for concrete database
     * @return
     */
    public abstract String hibernateDialect();

    /**
     * hibernate.hbm2ddl.auto
     * @return ''
     */
    public String hbm2ddlAuto() {
        return HBM2DDLAuto.NOTHING.getValue();
    }


    public void useEntityManagerBean(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        this.sessionFactory = ((org.hibernate.ejb.EntityManagerFactoryImpl)entityManagerFactoryBean.getNativeEntityManagerFactory()).getSessionFactory();
    }

    @Bean
    @DependsOn("entityManagerFactory")
    public SessionFactory sessionFactory() {
        return this.sessionFactory;
    }

}
