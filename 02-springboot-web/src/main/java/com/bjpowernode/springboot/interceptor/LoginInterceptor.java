package com.bjpowernode.springboot.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:LoginInterceptor
 * package:com.bjpowernode.springboot.interceptor
 * Descrption:
 *
 * @Date:2018/7/24 14:33
 * @Author:724班
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 进入controller之前执行该方法
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("进入了登录拦截器......");

        //业务处理的逻辑，根据不同项目，是不一样（省略）

        return true;
    }
}