package pe.gob.osce.rnp.seg.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static final String PROF_PROD = "production";
    private static final String PROF_PRE_PROD = "devclient";
    private static final String DIFF_TO_PRO_AND_PRE_PROD = "!"+ PROF_PROD + " & " + "!"+PROF_PRE_PROD;


    @Value("${api.bs.route}")
    private String apiBaseRoute;

    @Value("${datasource.jndi.primary:#{null}}")
    private String jndiNameProduction;

    @Value("${spring.profiles.active}")
    private String profile;

    @Profile(value = {PROF_PROD, PROF_PRE_PROD})
    @Bean(destroyMethod = "")
    public DataSource oauthDataSource() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource(jndiNameProduction);
    }

    @Profile(value = DIFF_TO_PRO_AND_PRE_PROD)
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource oauthDataSourceDev() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(checkIfProfileProdOrPreProdAreActived() ? oauthDataSource() : oauthDataSourceDev());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(checkIfProfileProdOrPreProdAreActived() ? oauthDataSource() : oauthDataSourceDev());
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(checkIfProfileProdOrPreProdAreActived() ? oauthDataSource() : oauthDataSourceDev());
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(checkIfProfileProdOrPreProdAreActived() ? oauthDataSource() : oauthDataSourceDev());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
        super.configure(oauthServer);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
       endpoints
                .approvalStore(approvalStore())
                .authorizationCodeServices(authorizationCodeServices())
                .tokenStore(tokenStore())//Registra el token
                .pathMapping("/oauth/token", apiBaseRoute+"/oauth/token");
        super.configure(endpoints);
    }

    public boolean checkIfProfileProdOrPreProdAreActived(){
        return profile.equals(PROF_PROD) || profile.equals(PROF_PRE_PROD);
    }
}
