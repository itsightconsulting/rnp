package pe.gob.osce.rnp.seg.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class ResourcesServerConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    public DataSource oauthDataSource;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        TokenStore tokenStore=new JdbcTokenStore(oauthDataSource);
        resources.resourceId("rnp_api").stateless(false).tokenStore(tokenStore);
        /*resources.authenticationEntryPoint(customAuthEntryPoint());*/ //Al colocar el entryPoint en este nivel la intercepcion aplica cuando se hace peticiones tipo rest asi como también peticiones simples sin el Authorization header
    }

    @Order(2)
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers("/api/**").and()
                .authorizeRequests().antMatchers("/api/**").access("#oauth2.hasScope('read')").and()
                .authorizeRequests().antMatchers( "/api/**").access("#oauth2.hasScope('write')")
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthEntryPoint());//Al colocar el entryPoint en esta configuración la intercepcion solo aplica cuando este se hace directamente desde un navegador o una simple http request sin el Authorization header
    }

    @Bean
    public AuthenticationEntryPoint customAuthEntryPoint(){
        return new OauthAuthenticationFilter();
    }
}
