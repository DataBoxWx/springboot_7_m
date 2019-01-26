package com.bjpowernode.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.springboot.constants.Constants;
import com.bjpowernode.springboot.model.UserInfo;
import com.bjpowernode.springboot.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:UserInfoController
 * package:com.bjpowernode.springboot.controller
 * Descrption:
 *
 * @Date:2018/7/28 15:28
 * @Author:724班
 */
@Controller
public class UserInfoController {

    @Reference //dubbo注解，引用远程的dubbo服务
    private UserInfoService userInfoService;

    /**
     * 我们将该综合案例全部遵循rest原则，通常情况下，使用rest风格都是用于开发接口的，给调用者返回json数据
     *
     *
     * restfull原则中：
     *
     * 1、url路径中不要动词
     * 2、添加数据、插入数据等新增操作使用 Post请求，对应的是 @PostMapping
     *    修改数据、更新数据等更新操作使用 Put请求，对应的是 @PutMapping
     *    删除 。。。。。。。。。。。。。。Delete请求，对应的是 @DeleteMapping
     *    查询 。。。。。。。。。。。。。。Get请求，对应的是 @GetMapping
     *
     * 3、如果要对数据进行过滤，比如分页，排序等，这个时候url路径就不需要遵循restfull原则了
     *    http://localhost:8080/api/orders?p=16
     *
     *
     * @param p
     * @return
     */
    @GetMapping("/all/users")
    public String users(@RequestParam(value = "p", required = false) Integer p, Model model) {

        if (null == p) {
            p = 1;
        }

        int startRow = (p - 1) * Constants.PAGE_SIZE;
        List<UserInfo> userInfoList = userInfoService.getUserInfoByPage(startRow, Constants.PAGE_SIZE);

        int totalRows = userInfoService.getUserInfoByTotal();

        //计算页数
        int totalPages = totalRows / Constants.PAGE_SIZE;
        int left = totalRows % Constants.PAGE_SIZE;
        if (left > 0) {
            totalPages = totalPages + 1;
        }

        model.addAttribute("userInfoList", userInfoList);
        //当前页
        model.addAttribute("p", p);
        model.addAttribute("totalPages", totalPages);

        //使用thymeleaf展示数据
        return "users";
    }

    /**
     * 去添加用户--页面跳转而已
     *
     * @return
     */
    @GetMapping("/all/user")
    public String toAddUser() {

        return "user";
    }

    /**
     * 添加用户--提交
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/all/user")
    public String addUser(UserInfo userInfo) {

        System.out.println("添加用户。。。。。。。");

        userInfoService.addUserInfo(userInfo);

        return "redirect:/all/users";
    }

    /**
     * 去修改
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/all/user/{id}")
    public String toUpdateUser(@PathVariable("id") Integer id, Model model) {

        UserInfo userInfo = userInfoService.getUserInfoById(id);

        model.addAttribute("userInfo", userInfo);

        return "user";
    }

    /**
     * 修改用户--提交
     *
     * @param userInfo
     * @return
     */
    @PutMapping("/all/user")
    public String updateUser(UserInfo userInfo) {

        System.out.println("修改用户。。。。。。。");

        userInfoService.updateUserInfo(userInfo);

        return "redirect:/all/users";
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("/all/user/{id}")
    public String deleteuser(@PathVariable("id") Integer id) {

        System.out.println("删除用户......");

        userInfoService.deleteUserInfoById(id);

        return "redirect:/all/users";
    }
}