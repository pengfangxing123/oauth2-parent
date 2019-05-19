//package com.oauth2.authorization.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.approval.ApprovalStore;
//import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
//import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
///**
// * @author Administrator
// * 获取授权：localhost:8080/oauth/authorize
// * 获取access_token:localhost:8080/oauth/token
// * /oauth/confirm_access（用户发布批准此处）
// * /oauth/authorize（授权端点）
// * /oauth/error（用于在授权服务器中呈现错误）
// * /oauth/check_token（由资源服务器用于解码访问令牌）
// * /oauth/token_key（如果使用JWT令牌，则公开用于令牌验证的公钥）
// *
// * ClientDetailsServiceConfigurer：用来配置客户端详情服务（ClientDetailsService），客户端详情信息在这里进行初始化，你能够把客户端详情信息写死在这里或者是通过数据库来存储调取详情信息
// *      in-memory：内存中
// *      JDBC：数据库中，可以通过
// *
// * AuthorizationServerEndpointsConfigurer:用来配置令牌端点(Token Endpoint)的安全约束.
// *
// * AuthorizationServerSecurityConfigurer:用来配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
// */
//@EnableAuthorizationServer
//@Configuration
//public class CodeAuthorizationConfig extends AuthorizationServerConfigurerAdapter {
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private ClientDetailsService clientDetailsService;
//
//    @Resource
//    private AuthenticationManager authenticationManager;
//
//    /**
//     * 配置jwt token加密
//     * 因为使用jwt 可以再token加入用户信息，所以需要进行加密
//     * 加密有对称加密个非对称加密（公钥，私钥），下面为对称加密
//     * @return
//     */
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter(){
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        //对称类型key
//        converter.setSigningKey("duichengjiami");//资源服务器也要进行配置
//        return converter;
//    };
//
//    /**
//     * 存储，查找approvals
//     * @return
//     */
//    @Bean
//    public ApprovalStore approvalStore(){
//        return new JdbcApprovalStore(dataSource);
//    }
//
//    /**
//     * token存储路径
//     * @return
//     */
//    @Bean
//    public TokenStore tokenStore(){
//        return new JdbcTokenStore(dataSource);
//    }
//
//    /**
//     * 授权码存储
//     * @return
//     */
//    @Bean
//    public AuthorizationCodeServices authorizationCodeServices()
//    {
//        return new JdbcAuthorizationCodeServices(this.dataSource);
//    }
//
//    /**
//     * 定义令牌端点上的安全约束
//     * 使用JWT令牌你需要在授权服务中使用 JwtTokenStore，资源服务器也需要一个解码的Token令牌的类 JwtAccessTokenConverter，
//     * JwtTokenStore依赖这个类来进行编码以及解码，因此你的授权服务以及资源服务都需要使用这个转换类。Token令牌默认是有签名的，
//     * 并且资源服务需要验证这个签名，因此呢，你需要使用一个对称的Key值，用来参与签名计算，这个Key值存在于授权服务以及资源服务之中。
//     * 或者你可以使用非对称加密算法来对Token进行签名，Public Key公布在/oauth/token_key这个URL连接中，默认的访问安全规则是"denyAll()"，
//     * 即在默认的情况下它是关闭的，你可以注入一个标准的 SpEL 表达式到 AuthorizationServerSecurityConfigurer 这个配置中来将它开启（例如使用"permitAll()"来开启可能比较合适，因为它是一个公共密钥）。
//     * @param security
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//        security.allowFormAuthenticationForClients();//允许表单登录
//    }
//
//    /**
//     * 定义客户端详细信息服务的配置程序
//     * @param clients
//     * @throws Exception
//     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
////        配多个到内存中会第二个会报badesecret的错误，待研究
////        clients.inMemory()
////                .withClient("ssoDemo")// client_id
////                .secret(bCryptPasswordEncoder.encode("ssoDemoSecret"))// client_secret
////                .authorizedGrantTypes("authorization_code")// 授权类型
////                .redirectUris("http://localhost:8081/resource/login")
////                .scopes("all")// 授权范围
////                .autoApprove(true)
////                .and().inMemory().withClient("sso")
////                .secret(bCryptPasswordEncoder.encode("ssoSecret"))
////                .authorizedGrantTypes("authorization_code")
////                .redirectUris("http://localhost:8082/resource/login")
////                .scopes("all")
////                .autoApprove(true);
//        clients.jdbc(dataSource);
//    }
//
//    /**
//     * 定义授权和令牌端点和令牌服务。
//     * @param endpoints
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints
//                .authenticationManager(this.authenticationManager)//支持密码授权模式
//                .accessTokenConverter(jwtAccessTokenConverter())//token加密
//                .approvalStore(approvalStore())//存储，查找授权信息
//                .authorizationCodeServices(authorizationCodeServices())//授权码存储
//                .tokenStore(tokenStore());//token存储路径
//                //.setClientDetailsService(this.clientDetailsService);//获取client信息
//    }
//}
//
