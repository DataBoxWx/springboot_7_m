package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.model.User;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:MVCController
 * package:com.bjpowernode.springboot.controller
 * Descrption:
 *
 * @Date:2018/7/23 17:47
 * @Author:724班
 */
@RestController // == @Controller + @RespoonseBody
public class MVCController {

    @RequestMapping("/boot/user")
    public User user() {

        User user = new User();

        user.setId(100);
        user.setPhone("13700000000");

        return user;
    }

    @RequestMapping(value="/boot/user1", method = RequestMethod.GET)
    public User user1() {

        User user = new User();

        user.setId(100);
        user.setPhone("13700000000");

        return user;
    }

    /**
     * 只支持Get请求
     *
     * @return
     */
    @GetMapping(value="/boot/user2")
    public User user2() {

        User user = new User();

        user.setId(100);
        user.setPhone("13700000000");

        return user;
    }

    /**
     * 只支持Post请求
     *
     * @return
     */
    @PostMapping(value="/boot/user3")
    public User user3() {

        User user = new User();

        user.setId(100);
        user.setPhone("13700000000");

        return user;
    }
}
