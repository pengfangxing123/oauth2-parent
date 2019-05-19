package com.oauth2.authorization.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author Administrator
 */
//@Configuration
public class ErrorPageConfig {

    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer(){
        return (WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>) factory -> factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,"/403"));
    }
}
