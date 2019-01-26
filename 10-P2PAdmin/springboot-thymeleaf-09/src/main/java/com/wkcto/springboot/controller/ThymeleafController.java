package com.wkcto.springboot.controller;

import com.wkcto.springboot.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ClassName:ThymeleafController
 * package:com.wkcto.springboot.controller
 * Description:
 *
 * @Data:2018/7/28 9:22
 * @Auther:wangxin
 */
@Controller
public class ThymeleafController {

    @GetMapping("/boot/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("msg","集成themyleaf成功");

        User user = new User();
        user.setId("11");
        user.setNick("小龙女");
        user.setPhone("13700000000000");
        user.setAddress("五道口职业技术学院");
        user.setEmail("ali@ali.com");
        model.addAttribute("user",user);
        return "index";
    }
}
