package modules.config.db.postgres;

import modules.config.db.ConcreteEntityManagerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User: lameroot
 * Date: 02.02.13
 * Time: 11:46
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public abstract class PostgresEntityManagerConfig extends ConcreteEntityManagerConfig {

    public static final String DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

    @Override
    public String hibernateDialect() {
        return DIALECT;
    }

}
