package modules.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public abstract class EmbeddedDataSourceConfig extends AbstractDataSourceConfig {

    @Bean
    @Override
    public DataSource dataSource() {
        log.debug("Start test datasource");

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase database = builder.setType(EmbeddedDatabaseType.H2)
//                .setName(getDatabaseName())
                //.addScript(getSchemaScriptUrl())
                .addScript(getDataScriptUrl())

                .build();

        return database;

    }

    public abstract String getDatabaseName();
    public abstract String getSchemaScriptUrl();
    public abstract String getDataScriptUrl();

}