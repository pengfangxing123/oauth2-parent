package com.oauth2.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Administrator
 */
@Configuration
@EnableWebSecurity //等于配置Security的filter,启动它的filter链
@EnableGlobalMethodSecurity(jsr250Enabled = true,securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * UsernamePasswordAuthenticationFilter->AuthenticationManager
     * ->AuthenticationProvider(校验获取到用户信息和UserDetailsService提供的信息)
     * ->AuthenticationSuccessHandler（登录成功处理）
     * ->AuthenticationFailureHandler（登录失败处理）
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(bCryptPasswordEncoder.encode("123.com")).roles("admin");
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 授权模式为client_credentials时是无关用户的，所以要全部放行
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .loginPage("/login")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and().csrf().disable().cors();
        http.csrf().disable().cors()
                .and().authorizeRequests()
                .antMatchers("/").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //允许静态静态资源的访问
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
