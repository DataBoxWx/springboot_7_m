package com.bjpowernode.p2p.admin.config;

import com.bjpowernode.p2p.admin.interceptor.LoginInterceptor;
import com.bjpowernode.p2p.admin.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 定义拦截器配置类
 * 
 * @author yanglijun
 * Configuration注解标注此文件为一个配置项，Spring boot才会扫描到该配置，该注解类似于之前使用xml进行配置
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        //要拦截的路径
        String[] patterns = {"/**"};

        //不拦截的路径
        String[] excludePathPatterns = {
                "/admin/login",
                "/admin/logout",
                "/admin/profile",
                "/"
        };

        //不需要权限拦截的url，也就是公共url，任何用户登录后都可以访问
        String[] excludePathPatternsForPermission = {
                "/admin/login",
                "/admin/logout",
                "/admin/profile",
                "/",
                "/admin/refuse"
        };

        //注册拦截器
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(patterns).excludePathPatterns(excludePathPatterns);
        //权限拦截器
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns(patterns).excludePathPatterns(excludePathPatternsForPermission);
    }
}