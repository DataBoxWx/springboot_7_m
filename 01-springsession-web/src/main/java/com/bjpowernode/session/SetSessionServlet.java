package com.bjpowernode.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 放置Session
 *
 * package:${PACKAGE_NAME}
 * Descrption:
 *
 * @Date:2018/7/21 16:35
 * @Author:724班
 */
@WebServlet(name = "SetSessionServlet", urlPatterns = "/setSession")
public class SetSessionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("url", "http://www.wkcto.com");
        response.getWriter().write("OK");
    }
}