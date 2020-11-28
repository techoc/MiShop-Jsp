package com.itqf.filter;

import com.itqf.entity.User;
import com.itqf.service.UserService;
import com.itqf.service.impl.UserServiceImpl;
import com.itqf.utils.Base64Utils;
import com.itqf.utils.Constants;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "AutoFilter", value = "/login.jsp")
public class AutoFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            //本地存储了cookie
            String content = null;  //为了接受存储的账号密码的数据
            for (Cookie cookie : cookies) {
                //如果找到存储自动登录的名字 就读取存储的账号和密码
                if (cookie.getName().equals(Constants.AUTO_NAME)) {
                    content = cookie.getValue();
                }
            }
            if (content != null) {
                //读取到了存储用户和密码的cookie
                content = Base64Utils.decode(content);
                String[] split = content.split(Constants.FLAG);
                String username = split[0];
                String password = split[1];

                UserService userService = new UserServiceImpl();
                try {
                    User user = userService.login(username, password);
                    if (user != null) {
                        //可以自动登录
                        HttpSession session = request.getSession();
                        session.setAttribute("loginUser", user);
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    } else {
                        chain.doFilter(req, resp);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            //本地没有cookie 放行
            chain.doFilter(req, resp);
        }

    }

}
