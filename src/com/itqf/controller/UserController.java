package com.itqf.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * UserController对应的不是某一个具体的方法和功能！
 * 他要实现的是用户某块的所有业务逻辑！
 * 例如：登录，注册！
 */
@WebServlet(name = "UserController", value = "/user")
public class UserController extends BaseServlet {

    //登录方法
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("登录业务逻辑");

    }

    //注册方法
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("注册业务逻辑");
    }
}
