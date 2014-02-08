package modules.config.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * User: lameroot
 * Date: 30.01.13
 * Time: 22:31
 */
public abstract class SimpleDataSourceConfig extends AbstractDataSourceConfig {

    @Bean(destroyMethod="close",name="dataSource")
    public DataSource dataSource() {
        log.debug("Start development datasource");
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(getDriverClassName());
        dataSource.setUrl(getUrl());
        dataSource.setUsername(getUsername());
        dataSource.setPassword(getPassword());

        dataSource.setDefaultAutoCommit(false);
        dataSource.setDefaultTransactionIsolation(2);

        dataSource.setInitialSize(0);
        dataSource.setMinIdle(0);
        dataSource.setMaxWait(5000);

        dataSource.setValidationQuery(getValidationQuery());
        dataSource.setTimeBetweenEvictionRunsMillis(600000);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);

        dataSource.setPoolPreparedStatements(true);

        return dataSource;
    }

    public abstract String getValidationQuery();
    public abstract String getDriverClassName();
    public abstract String getUrl();
    public abstract String getUsername();
    public abstract String getPassword();



}
