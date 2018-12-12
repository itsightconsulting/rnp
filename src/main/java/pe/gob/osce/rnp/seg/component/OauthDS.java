package pe.gob.osce.rnp.seg.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("oauthDs")
public class OauthDS implements OauthDataSource {

    @Override
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDS() {
        return DataSourceBuilder.create().build();
    }
}
