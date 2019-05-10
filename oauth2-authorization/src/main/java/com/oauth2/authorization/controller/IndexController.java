package com.oauth2.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * @author Administrator
 */
@Controller

public class IndexController {

    @GetMapping("/index/getRandom")
    @ResponseBody
    public int getRandom(){
        return new Random().nextInt(100);
    }
}
