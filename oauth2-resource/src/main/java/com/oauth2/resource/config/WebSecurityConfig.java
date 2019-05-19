//package com.oauth2.resource.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * sso第二个服务
// * 懒得再建项目，把sso的配置先注释掉
// * @author Administrator
// *
// */
//@Configuration
//@EnableOAuth2Sso
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.logout().logoutSuccessUrl("http://localhost:8080/logout")
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//    }
//}
