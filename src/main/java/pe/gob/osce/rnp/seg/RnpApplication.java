package pe.gob.osce.rnp.seg;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

@SpringBootApplication
@Configuration
@EnableMBeanExport(registration= RegistrationPolicy.IGNORE_EXISTING)
public class RnpApplication extends SpringBootServletInitializer /*implements CommandLineRunner */{

    @Value("${datasource.jndi.primary:#{null}}")
    private String jndiNameProduction;



    @Value("${spring.profiles.active}")
    private String profileActive;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(RnpApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(RnpApplication.class, args);
    }


    @Profile(value = "production")
    @Bean(destroyMethod = "")
    @Primary
    public DataSource mainDataSource() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource(jndiNameProduction);
    }

    @Profile(value = "!production")
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mainDataSourceDev() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        //config.setAllowCredentials(true);
        String directionAllowOrigin = profileActive.equals("production") ? "https://apps.osce.gob.pe" : "*";
        config.addAllowedOrigin(directionAllowOrigin);
        source.registerCorsConfiguration("/api/**", config);
        return new CorsFilter(source);
    }
}
