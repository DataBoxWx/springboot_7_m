package com.bjpowernode.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:HelloController
 * package:com.bjpowernode.springboot.controller
 * Descrption:
 *
 * @Date:2018/7/23 16:12
 * @Author:724班
 */
@Controller
public class HelloController {

    @RequestMapping("/boot/hello")
    public @ResponseBody String hello () {

        return "Hello, Spring Boot.";
    }
}