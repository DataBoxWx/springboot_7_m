package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.config.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:ConfigInfoController
 * package:com.bjpowernode.springboot.controller
 * Descrption:
 *
 * @Date:2018/7/23 16:44
 * @Author:724班
 */
@Controller
public class ConfigInfoController {

    @Value("${info.site.url}")
    private String url;

    @Value("${info.site.tel}")
    private String tel;

    @Autowired
    private ConfigInfo configInfo;

    /**
     * 读取自定义配置
     *
     * @return
     */
    @RequestMapping("/boot/config")
    public @ResponseBody String config () {
        return "config info: " + url + "--" + tel + "-->" + configInfo.getUrl() + "--" + configInfo.getTel();
    }
}
