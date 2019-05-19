package com.oauth2.resource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author Administrator
 */
@Configuration
@EnableResourceServer
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter {
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //对称类型key
        converter.setSigningKey("duichengjiami");
        return converter;
    };

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("reource_id");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
