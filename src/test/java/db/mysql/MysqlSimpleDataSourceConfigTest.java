package db.mysql;

import db.AbstractConfigTest;
import modules.config.db.mysql.MysqlSimpleDataSourceConfig;
import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: lameroot
 * Date: 02.02.13
 * Time: 11:59
 */
@ContextConfiguration(
        classes = {
                MysqlSimpleDataSourceConfigTest.MysqlConfig.class,
                MysqlSimpleDataSourceConfigTest.MysqlLocalhostConfig.class,
        }
)
@ActiveProfiles(profiles = {"mysql_localhost"})
public class MysqlSimpleDataSourceConfigTest extends AbstractConfigTest {

    @Resource
    private DataSource dataSource;

    @Test
    public void testExistsDataSource() {
        assertNotNull(dataSource);
    }

    @Test
    public void testExecuteQuery() {
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = dataSource.getConnection().prepareStatement("select 1 from dual");
            if ( null != pstm ) {
                rs = pstm.executeQuery();
                while (rs.next()) {
                    assertEquals(1,rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.error("",e);
        }
        if ( null != pstm ) {
            try {
                pstm.close();
                pstm = null;
            } catch (SQLException e) {
                logger.error("",e);
            }
        }
        if ( null != rs ) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("",e);
            }
        }
    }

    @Configuration
    public static class MysqlConfig extends MysqlSimpleDataSourceConfig {
        @Override
        public String getSchemaName() {
            return "yes_base";
        }

        @Override
        public String getPassword() {
            return "";
        }

        @Override
        public String getUsername() {
            return "root";
        }

        @Override
        public String getIpAddress() {
            return "localhost";
        }
    }

    @Configuration
    @Profile(value = "mysql_localhost")
    public static class MysqlLocalhostConfig extends MysqlSimpleDataSourceConfig {
        @Override
        public String getSchemaName() {
            return "skrainov_main";
        }

        @Override
        public String getPassword() {
            return "skrainovnoob";
        }

        @Override
        public String getUsername() {
            return "skrainov";
        }

        @Override
        public String getIpAddress() {
            return "localhost";
        }
    }
}
