package com.bjpowernode.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName:JSPController
 * package:com.bjpowernode.springboot.controller
 * Descrption:
 *
 * @Date:2018/7/23 18:01
 * @Author:724班
 */
@Controller
public class JSPController {

    @GetMapping("/boot/jsp")
    public String index (Model model) {

        model.addAttribute("msg", "Spring Boot集成使用JSP.");

        return "index";
    }
}
