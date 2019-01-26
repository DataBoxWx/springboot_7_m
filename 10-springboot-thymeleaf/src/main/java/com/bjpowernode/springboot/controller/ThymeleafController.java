package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ThymeleafController
 * package:com.bjpowernode.springboot.controller
 * Descrption:
 *
 * @Date:2018/7/27 15:16
 * @Author:724班
 */
@Controller
public class ThymeleafController {

    @GetMapping("/boot/thymeleaf")
    public String thymeleaf(HttpSession session, Model model) {

        model.addAttribute("msg", "springboot整合thymeleaf成功.");

        User user = new User();
        user.setId(1);
        user.setNick("昵称");
        user.setPhone("13700020000");
        user.setAddress("北京大兴");

        model.addAttribute("user", user);
        model.addAttribute("hello", "helloworld");

        //把model里面放一个List集合数据
        List<User> userList = new ArrayList<>();

        Map<String, Object> userMap = new HashMap<>();

        User[] userArray = new User[10];

        for (int i=0; i<10; i++) {
            User u = new User();
            u.setId(1);
            u.setNick("昵称");
            u.setPhone("13700020000");
            u.setAddress("北京大兴");

            userList.add(u);

            userMap.put(String.valueOf(i), u);

            userArray[i] = u;
        }
        model.addAttribute("userList", userList);

        model.addAttribute("userMap", userMap);

        model.addAttribute("userArray", userArray);


        //我有一个Map，map里面放了几个List，List里面放了几个对象
        Map<String, Object> map = new HashMap<>();

        List<User> userList1 = new ArrayList<>();
        userList1.add(user);
        userList1.add(user);
        userList1.add(user);

        List<User> userList2 = new ArrayList<>();
        userList2.add(user);
        userList2.add(user);
        userList2.add(user);

        map.put("1", userList1);
        map.put("2", userList2);

        model.addAttribute("map", map);

        model.addAttribute("sex", 1); //1男 2女


        session.setAttribute("user", user);
        //返回到的是 index.html 的thymeleaf模板页面
        return "index";
    }
}
