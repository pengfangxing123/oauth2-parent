package com.oauth2.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 */
@Controller
public class IndexController {
    @GetMapping("/index/getStr")
    @ResponseBody
    public String getStr(){
        return "成功获取到资源";
    }
}
