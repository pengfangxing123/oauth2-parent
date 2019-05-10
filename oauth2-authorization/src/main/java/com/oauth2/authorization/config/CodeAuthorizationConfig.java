package com.oauth2.authorization.config;

import com.oauth2.common.constant.GrantTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @author Administrator
 * 获取授权：localhost:8080/oauth/authorize
 * 获取access_token:localhost:8080/oauth/token
 */
@EnableAuthorizationServer
@Configuration
public class CodeAuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


//    /**
//     * 授权码模式
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients
//                .inMemory()
//                .withClient("client")
//                .secret(bCryptPasswordEncoder.encode("secret"))
//                .redirectUris("http://localhost:8080/index/getRandom")
//                .authorizedGrantTypes(GrantTypes.AUTHORIZATION_CODE)
//                .scopes("all");
//    }



    /** 密码模式*/
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("client")
                .secret(bCryptPasswordEncoder.encode("secret"))
                .authorizedGrantTypes(GrantTypes.PASSWORD)
                .scopes("all","haha");
    }



}

