package com.oauth2.resource.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 */
@Controller
public class IndexContrller {

    @GetMapping("/index/getUser")
    @ResponseBody
    public Authentication getUser(Authentication authentication){
        return authentication;
    }

    @GetMapping("/index/getStr")
    @ResponseBody
    public String getStr(){
        return "获取到资源";
    }
}
