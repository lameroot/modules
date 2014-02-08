package modules.config.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * User: lameroot
 * Date: 30.01.13
 * Time: 21:58
 */
public abstract class AbstractDataSourceConfig {

    @Resource
    protected Environment environment;
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    public abstract DataSource dataSource();

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource(),true);
    }
}
