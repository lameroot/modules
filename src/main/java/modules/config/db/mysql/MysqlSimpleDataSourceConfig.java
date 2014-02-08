package modules.config.db.mysql;


import modules.config.db.ConcreteSimpleDataSourceConfig;

/**
 * User: lameroot
 * Date: 30.01.13
 * Time: 22:35
 */
public abstract class MysqlSimpleDataSourceConfig extends ConcreteSimpleDataSourceConfig {

    /**
     *
     * @return SELECT 1 FROM DUAL
     */
    @Override
    public String getValidationQuery() {
        return "SELECT 1 FROM DUAL";  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     *
     * @return com.mysql.jdbc.Driver
     */
    @Override
    public String getDriverClassName() {
        return "com.mysql.jdbc.Driver";
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
        return "mysql";
    }

    @Override
    public int getPort() {
        return 3306;
    }
}

