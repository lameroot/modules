package modules.config.db.mysql;

import modules.config.db.ConcreteEntityManagerConfig;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.MySQLInnoDBDialect;
import org.hibernate.dialect.MySQLMyISAMDialect;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User: lameroot
 * Date: 02.02.13
 * Time: 11:17
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public abstract class MysqlEntityManagerConfig extends ConcreteEntityManagerConfig {

    public final static String DEFAULT_DIALECT          = MySQL5Dialect.class.getName();
    public final static String MYSQL5_INNODB_DIALECT    = MySQL5InnoDBDialect.class.getName();
    public final static String MYSQL5_MYISAM_DIALECT    = MySQL5MyISAMDialect.class.getName();
    public final static String INNODB_DIALECT           = MySQLInnoDBDialect.class.getName();
    public final static String MYISAM_DIALECT           = MySQLMyISAMDialect.class.getName();

    /**
     *
     * @return org.hibernate.dialect.MySQLInnoDBDialect
     */
    public String hibernateDialect() {
        return MYSQL5_INNODB_DIALECT;
    }
}
