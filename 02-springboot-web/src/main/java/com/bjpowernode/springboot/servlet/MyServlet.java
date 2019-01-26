package com.bjpowernode.springboot.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName:${NAME}
 * package:${PACKAGE_NAME}
 * Descrption:
 *
 * @Date:2018/7/24 14:46
 * @Author:724班
 */
@WebServlet(name = "MyServlet",urlPatterns = "/myServlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("my servlet ...... 北京动力节点");
    }
}