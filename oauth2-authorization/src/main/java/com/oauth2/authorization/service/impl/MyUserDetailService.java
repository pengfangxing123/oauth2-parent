package com.oauth2.authorization.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component("userDetailsService")
@Primary
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=new User("admin",bCryptPasswordEncoder.encode("123.com"),AuthorityUtils.commaSeparatedStringToAuthorityList("add,edit"));
        return user;
    }
}
