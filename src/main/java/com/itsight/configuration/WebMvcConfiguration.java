package com.itsight.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {


    @Value("${caching}")
    private boolean caching;

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
}
