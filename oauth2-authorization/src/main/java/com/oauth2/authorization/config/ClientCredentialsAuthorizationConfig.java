package com.oauth2.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Administrator
 */
@EnableAuthorizationServer
@Configuration
public class ClientCredentialsAuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 配置jwt token加密
     * 因为使用jwt 可以再token加入用户信息，所以需要进行加密
     * 加密有对称加密个非对称加密（公钥，私钥），下面为对称加密
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //对称类型key
        converter.setSigningKey("duichengjiami");//资源服务器也要进行配置
        return converter;
    };

    /**
     * 存储，查找approvals
     * @return
     */
    @Bean
    public ApprovalStore approvalStore(){
        return new JdbcApprovalStore(dataSource);
    }

    /**
     * token存储路径
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 授权码存储
     * @return
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices()
    {
        return new JdbcAuthorizationCodeServices(this.dataSource);
    }

    /**
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients();//允许表单登录
    }

    /**
     * 定义客户端详细信息服务的配置程序
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
         clients.inMemory().withClient("client_credential")
                 .resourceIds("reource_id")
                 .authorizedGrantTypes("client_credentials", "refresh_token")
                 .scopes("all")
                 .authorities("client")
                 .secret(bCryptPasswordEncoder.encode("client_credential"))
                 .accessTokenValiditySeconds(7200);

    }
    //http://localhost:8080/oauth/authorize?client_id=client_credentials&response_type=client_credentials&scope=all&client_secret=client_credentials
    //http://localhost:8080/oauth/authorize?client_id=client_credentials&redirect_uri=https:www.baidu.com&response_type=client_credentials&scope=all
    /**
     * 定义授权和令牌端点和令牌服务。
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //.authenticationManager(this.authenticationManager)//支持密码授权模式
                .accessTokenConverter(jwtAccessTokenConverter())//token加密
                //.approvalStore(approvalStore())//存储，查找授权信息
                //.authorizationCodeServices(authorizationCodeServices())//授权码存储
                .tokenStore(tokenStore());//token存储路径
                //.setClientDetailsService(this.clientDetailsService);//获取client信息
    }
}
