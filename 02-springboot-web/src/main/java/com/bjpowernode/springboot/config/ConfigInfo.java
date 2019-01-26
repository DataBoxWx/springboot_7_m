package com.bjpowernode.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName:ConfigInfo
 * package:com.bjpowernode.springboot.config
 * Descrption:
 *
 * @Date:2018/7/23 16:49
 * @Author:724班
 */
@Component //把该model对象变成spring的一个bean
@ConfigurationProperties(prefix="info.site")
public class ConfigInfo {

    private String url;

    private String tel;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}