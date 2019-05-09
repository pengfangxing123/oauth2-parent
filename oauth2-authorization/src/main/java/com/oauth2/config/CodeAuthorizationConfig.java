package com.oauth2.config;

import common.GrantTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author Administrator
 * 获取授权：localhost:8080/oauth/authorize
 * 获取access_token:localhost:8080/oauth/token
 */
@EnableAuthorizationServer
@Configuration
public class CodeAuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder vCryptPasswordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("client")
                .secret(vCryptPasswordEncoder.encode("secret"))
                .redirectUris("http://localhost:8080/index/getRandom")
                .authorizedGrantTypes(GrantTypes.AUTHORIZATION_CODE)
                .scopes("all");
    }




}

