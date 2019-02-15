package pe.gob.osce.rnp.seg.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        pe.gob.osce.rnp.seg.cfg.CorsFilter filter = new pe.gob.osce.rnp.seg.cfg.CorsFilter();
        return filter;
    }


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/webjars/**","/resources/**");
    }

    @Order(1)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http
                .addFilterBefore(corsFilterCustom(), SessionManagementFilter.class);*/
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/session-expirada").permitAll()
                .antMatchers("/session-multiple").permitAll()
                .antMatchers("/api/**").permitAll();//Include: /api/oauth/token

        http.authorizeRequests()
                .anyRequest().authenticated();
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginCheck")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .deleteCookies("SESSION")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accesoDenegado")
                .authenticationEntryPoint(new AjaxAuthenticationFilter("/login"))
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
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    /*@Order(0)
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.0:4200"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(Long.valueOf(3600));
        configuration.setExposedHeaders(Arrays.asList("x-requested-with","x-requested-with, authorization","x-auth-token", "Authorization"));
        configuration.applyPermitDefaultValues();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/
}

