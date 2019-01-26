package com.bjpowernode.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * ClassName:SessionController
 * package:com.bjpowernode.springboot.controller
 * Descrption:
 *
 * @Date:2018/7/26 17:43
 * @Author:724班
 */
@RestController
public class SessionController {

    @GetMapping("/boot/setSession")
    public String setSession(HttpSession session) {
        session.setAttribute("url", "http://www.wkcto.com");

        return "OK";
    }

    @GetMapping("/boot/getSession")
    public String getSession(HttpSession session) {
        String url = (String)session.getAttribute("url");

        return null == url ? "no session" : url;
    }
}
