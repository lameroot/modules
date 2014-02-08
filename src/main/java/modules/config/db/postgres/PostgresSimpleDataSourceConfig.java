package modules.config.db.postgres;


import modules.config.db.ConcreteSimpleDataSourceConfig;

/**
 * User: lameroot
 * Date: 02.02.13
 * Time: 11:29
 */
public abstract class PostgresSimpleDataSourceConfig extends ConcreteSimpleDataSourceConfig {

    /**
     *
     * @return SELECT 1
     */
    @Override
    public String getValidationQuery() {
        return "SELECT 1";
    }

    /**
     *
     * @return org.postgresql.Driver
     */
    @Override
    public String getDriverClassName() {
        return "org.postgresql.Driver";
    }

    /**
     *
     * @return root
     */
    @Override
    public String getUsername() {
        return "root";
    }

    /**
     *
     * @return 'empty'
     */
    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getJdbcTypeDatabase() {
        return "postgresql";
    }

    @Override
    public int getPort() {
        return 5432;
    }
}