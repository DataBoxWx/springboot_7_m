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
@WebServlet(name = "GetSessionServlet", urlPatterns = "/getSession")
public class GetSessionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = (String)request.getSession().getAttribute("url");
        response.getWriter().write(url == null ? "no session" : url);
    }
}