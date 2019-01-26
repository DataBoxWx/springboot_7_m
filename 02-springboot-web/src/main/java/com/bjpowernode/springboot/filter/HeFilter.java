package com.bjpowernode.springboot.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * ClassName:HeFilter
 * package:com.bjpowernode.springboot.filter
 * Descrption:
 *
 * @Date:2018/7/24 15:06
 * @Author:724班
 */
public class HeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("he filter ......");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
