package pe.gob.osce.rnp.seg.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    public static final String LOGIN_PATH = "login";

    @Autowired
    @Qualifier("securityUserServiceImpl")
    private UserDetailsService securityService;

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() {
        return securityService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    pe.gob.osce.rnp.seg.cfg.CorsFilter corsFilterCustom() {
        return new pe.gob.osce.rnp.seg.cfg.CorsFilter();
    }

    @Value("${api.bs.route}")
    private String apiBaseRoute;


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/webjars/**","/resources/**");
    }

    @Order(1)
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/session-expirada").permitAll()
                .antMatchers("/session-multiple").permitAll()
                .antMatchers("/api/**").permitAll();//Include: /api/oauth/token

        http.authorizeRequests()
                .antMatchers(
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/fonts/**",
                        "/sound/**"
                ).permitAll()
                .anyRequest().authenticated();
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/"+LOGIN_PATH)
                .loginProcessingUrl("/loginCheck")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .deleteCookies("SESSION")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/"+LOGIN_PATH).permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accesoDenegado")
                .authenticationEntryPoint(new AjaxAuthenticationFilter("/"+LOGIN_PATH))
                .and()
                .sessionManagement()
                .maximumSessions(5)
                .expiredUrl("/login?error=session-expired")
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());
    }

    // Work around https://jira.spring.io/browse/SEC-2855
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}

