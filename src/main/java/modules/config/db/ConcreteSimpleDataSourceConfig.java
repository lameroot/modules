package modules.config.db;

/**
 * User: lameroot
 * Date: 02.02.13
 * Time: 11:36
 */
public abstract class ConcreteSimpleDataSourceConfig extends SimpleDataSourceConfig {

    /**
     *
     * @return "jdbc:" + getJdbcTypeDatabase() + "://" + getIpAddress() + ":" + getPort() + "/" + getSchemaName() + "?autoReconnect=true&amp;characterEncoding=UTF-8"
     */
    @Override
    public String getUrl() {
        return new StringBuilder()
                .append("jdbc:")
                .append(getJdbcTypeDatabase())
                .append("://")
                .append(getIpAddress())
                .append(":")
                .append(getPort())
                .append("/")
                .append(getSchemaName())
                .append("?autoReconnect=true&amp;characterEncoding=UTF-8")
                .toString();
    }

    /**
     *
     * @return 127.0.0.1
     */
    public String getIpAddress() {
        return "127.0.0.1";
    }

    /**
     * Have to return postgresql or mysql
     * @return
     */
    public abstract String getJdbcTypeDatabase();
    public abstract int getPort();
    public abstract String getSchemaName();
}
