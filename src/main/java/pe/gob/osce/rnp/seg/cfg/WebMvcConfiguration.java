package pe.gob.osce.rnp.seg.cfg;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import pe.gob.osce.rnp.seg.model.jpa.Parametro;
import pe.gob.osce.rnp.seg.svc.ParametroService;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private static final Logger LOGGER = LogManager.getLogger(WebMvcConfiguration.class);

    @Value("${caching}")
    private boolean caching;

    @Value("${spring.profiles.active}")
    private String profileActive;

    @Autowired
    private ServletContext context;

    @Autowired
    private ParametroService parametroService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if(caching)
            registry.addResourceHandler("/img/**",
                    "/fonts/**",
                    "/css/**",
                    "/js/**")
                    .addResourceLocations(
                            "classpath:/static/img/",
                            "classpath:/static/fonts/",
                            "classpath:/static/css/",
                            "classpath:/static/js/")
                    .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS)).resourceChain(true)
                    .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
        else
            registry.addResourceHandler("/img/**",
                    "/fonts/**")
                    .addResourceLocations(
                            "classpath:/static/img/",
                            "classpath:/static/fonts/")
                    .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS)).resourceChain(true)
                    .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jacksonMessageConverter());
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }

    @Bean
    public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        MappingJackson2HttpMessageConverter messageConverter =
                new MappingJackson2HttpMessageConverter();

        List<MediaType> supportedMediaTypes=new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);

        messageConverter.setSupportedMediaTypes(supportedMediaTypes);
        messageConverter.setObjectMapper(new HibernateAwareObjectMapper());
        messageConverter.setPrettyPrint(true);

        return messageConverter;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        try{
            if(profileActive.equals("production") || profileActive.equals("devclient")){

                //MAIL PROPERTIES INTO THE CONTEXT
                addingInitialParameters();
                context.setAttribute("MAIL_HOST", parametroService.findByParametro("MAIL_HOST").getValor());
                context.setAttribute("MAIL_PORT", parametroService.findByParametro("MAIL_PORT").getValor());
                context.setAttribute("MAIL_USERNAME", parametroService.findByParametro("MAIL_USERNAME").getValor());
                context.setAttribute("MAIL_PASSWORD", parametroService.findByParametro("MAIL_PASSWORD").getValor());

                props.put("mail.smtp.ssl.trust", context.getAttribute("MAIL_HOST").toString());
                props.put("mail.smtp.EnableSSL.enable","true");
                /*if(!profileActive.equals("production")) props.put("mail.smtp.EnableSSL.enable","true");//Just(or not) for production environment*/
                mailSender.setHost(context.getAttribute("MAIL_HOST").toString());
                mailSender.setPort(Integer.parseInt(context.getAttribute("MAIL_PORT").toString()));
                mailSender.setUsername(context.getAttribute("MAIL_USERNAME").toString());
                mailSender.setPassword(context.getAttribute("MAIL_PASSWORD").toString());
                props.put("mail.debug", profileActive.equals("production") ? "false" : "true");
            }  else {
                addingInitialParameters();
                context.setAttribute("MAIL_USERNAME", "contoso.peru@gmail.com");
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                mailSender.setHost("smtp.gmail.com");
                mailSender.setPort(587);
                mailSender.setUsername("contoso.peru@gmail.com");
                mailSender.setPassword("p1ls3n@147");
                props.put("mail.debug", "true");
            }
        } catch (Exception ex){
            LOGGER.info("CONFIGURATION MAIL EXCEPTION: "+ex.getMessage());
            ex.printStackTrace();
        }
        return mailSender;
    }

    public void addingInitialParameters(){
        if(parametroService.findByParametro("MAIL_HOST") == null){
            parametroService.save(new Parametro("MAIL_HOST","smtp.gmail.com"));
        }
        if(parametroService.findByParametro("MAIL_PORT") == null){
            parametroService.save(new Parametro("MAIL_PORT","587"));
        }
        if(parametroService.findByParametro("MAIL_USERNAME") == null){
            parametroService.save(new Parametro("MAIL_USERNAME","egmp.rnp.clave@gmail.com"));
        }
        if(parametroService.findByParametro("MAIL_PASSWORD") == null){
            parametroService.save(new Parametro("MAIL_PASSWORD","Ernp2019"));
        }
        if(parametroService.findByParametro("WS_SUNAT_USERNAME") == null){
            parametroService.save(new Parametro("WS_SUNAT_USERNAME","sunat_ws_username"));
        }

        if(parametroService.findByParametro("WS_SUNAT_PASSWORD") == null){
            parametroService.save(new Parametro("WS_SUNAT_PASSWORD","sunat_ws_password"));
        }
    }
}
