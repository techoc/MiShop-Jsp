package com.itqf.controller;

import com.itqf.service.UserService;
import com.itqf.service.impl.UserServiceImpl;
import com.itqf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户模块的controller
 */
@WebServlet(name = "UserController", value = "/user")
public class UserController extends BaseServlet {
    public String check(HttpServletRequest request, HttpServletResponse response) {
        //1.获取用户名
        String username = request.getParameter("username");
        if (username == null) {
            return Constants.HAS_USER;
        }

        //2.调用业务逻辑判断用户名是否存在
        UserService userService = new UserServiceImpl();
        boolean b = userService.checkedUser(username);

        //3.响应字符串 1 存在 0 不存在
        if (b) {
            return Constants.HAS_USER;
        }

        return Constants.NOT_HAS_USER;
    }
}
