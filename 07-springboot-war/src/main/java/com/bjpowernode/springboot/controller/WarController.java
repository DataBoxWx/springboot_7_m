package com.bjpowernode.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:WarController
 * package:com.bjpowernode.springboot.controller
 * Descrption:
 *
 * @Date:2018/7/26 16:52
 * @Author:724班
 */
@Controller
public class WarController {

    @RequestMapping("/boot/json")
    public @ResponseBody Object json () {

        Map<String, Object> map = new HashMap<>();
        map.put("id", 1009);
        map.put("name", "zhangsan");

        return map;

    }

    @RequestMapping("/boot/jsp")
    public String jsp (Model model) {

        model.addAttribute("msg", "spring boot项目打war包");

        return "index";
    }
}
