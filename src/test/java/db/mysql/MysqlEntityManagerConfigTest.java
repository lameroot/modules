package db.mysql;

import db.AbstractConfigTest;
import modules.config.db.mysql.MysqlEntityManagerConfig;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User: lameroot
 * Date: 02.02.13
 * Time: 12:13
 */
@ContextConfiguration(classes = {
        MysqlEntityManagerConfigTest.MysqlEntityConfig.class,
        MysqlSimpleDataSourceConfigTest.MysqlConfig.class,
        MysqlSimpleDataSourceConfigTest.MysqlLocalhostConfig.class,
})
@ActiveProfiles(profiles = {"mysql_localhost"})
public class MysqlEntityManagerConfigTest extends AbstractConfigTest {

    @PersistenceContext
    private EntityManager em;
    @Resource
    private EntityManagerFactory sessionFactory1;

    @Test
    public void testExists() {
        assertNotNull(em);
        assertNotNull(sessionFactory1);
    }


    //@Test
    @Transactional
    @Rollback(false)
    public void testCreate() {
        UserDetailsTest userDetailsTest = new UserDetailsTest();
        userDetailsTest.setUsername("name");
        userDetailsTest.setPassword("passwiord");

    }

    @Configuration
    @EnableAspectJAutoProxy
    @EnableTransactionManagement
    public static class MysqlEntityConfig extends MysqlEntityManagerConfig {
        @Override
        public String[] getPackagesToScan() {
            return new String[]{
                    "modules.config.db.mysql",
                    };
        }

        @Override
        public String hbm2ddlAuto() {
            return HBM2DDLAuto.UPDATE.getValue();
        }

        @Override
        public boolean showSql() {
            return true;
        }
    }
}
