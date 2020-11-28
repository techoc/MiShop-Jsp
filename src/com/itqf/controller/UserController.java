package com.itqf.controller;

import com.itqf.entity.User;
import com.itqf.service.UserService;
import com.itqf.service.impl.UserServiceImpl;
import com.itqf.utils.Base64Utils;
import com.itqf.utils.Constants;
import com.itqf.utils.MD5Utils;
import com.itqf.utils.RandomUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * 用户模块的controller
 */
@WebServlet(name = "UserController", value = "/user")
public class UserController extends BaseServlet {
    /**
     * 检测用户名是否存在
     *
     * @return register.jsp页面的ajax 1存在 0不存在
     */
    public String check(HttpServletRequest request, HttpServletResponse response) throws SQLException {
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

    /**
     * 用户注册并发送激活邮件
     *
     * @return 重定向到注册成功页面
     */
    public String register(HttpServletRequest request, HttpServletResponse response) {
        //1.获取用户信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //2.完善用户信息
        //已经赋值：用户名 密码 邮箱 性别
        //没有赋值：激活状态 账号类型 激活码
        user.setU_status(Constants.USER_NOT_ACTIVE);  //未激活状况 0 激活 1
        user.setU_role(Constants.ROLE_CUSTOMER);      //普通客户0 管理员1
        user.setU_code(RandomUtils.createActive());

        //需要处理的属性
        user.setU_password(MD5Utils.md5(user.getU_password()));

        //3.调用业务逻辑注册
        UserService userService = new UserServiceImpl();
        try {
            userService.registerUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("registerMsg", "注册失败！");
            return Constants.FORWARD + "/register.jsp";
        }

        //4.响应
        return Constants.FORWARD + "/registerSuccess.jsp";
    }

    /**
     * 邮箱激活用户
     *
     * @return 重定向到message.jsp
     */
    public String active(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.获取激活码
        String c = request.getParameter("c");
        //反转Base64
        String code = Base64Utils.decode(c);

        //2.调用激活的业务逻辑
        UserService userService = new UserServiceImpl();
        int i = userService.activeUser(code);

        //3.响应(激活失败 激活码错误 激活码已经激活 | 激活成功)
        if (i == Constants.ACTIVE_FAIL) {
            request.setAttribute("msg", "未激活成功！");
        } else if (i == Constants.ACTIVE_SUCCESS) {
            request.setAttribute("msg", "激活成功，请登录！");
        } else {
            request.setAttribute("msg", "已经激活了！");
        }
        return Constants.FORWARD + "/message.jsp";
    }

    /**
     * 用户登录
     *
     * @return 重定向index.jsp
     */
    public String login(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code"); //验证码
        String auto = request.getParameter("auto"); //自动登录

        HttpSession session = request.getSession();
        String realCode = (String) session.getAttribute("code");


        //2.判断验证码是否正确
        if (code == null || !code.equalsIgnoreCase(realCode)) {
            request.setAttribute("msg", "验证码错误！");
            return Constants.FORWARD + "/login.jsp";
        }

        //3.调用业务逻辑判断账号密码
        UserService userService = new UserServiceImpl();
        User user = userService.login(username, password);

        //4.响应
        if (user == null) {
            request.setAttribute("msg", "账号或者密码错误");
            return Constants.FORWARD + "/login.jsp";
        }
        if (user.getU_status().equals(Constants.USER_NOT_ACTIVE)) {
            request.setAttribute("msg", "账号未激活");
            return Constants.FORWARD + "/login.jsp";
        }
        session.setAttribute("loginUser", user);

        //判断是否勾选自动登录
        if (auto == null) {
            //没有勾选自动登录
            //将本地浏览器里的cookie清空
            Cookie cookie = new Cookie(Constants.AUTO_NAME, "");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        } else {
            //自动登录数据存储 2周
            String contant = username + Constants.FLAG + password;
            contant = Base64Utils.encode(contant);
            Cookie cookie = new Cookie(Constants.AUTO_NAME, contant);
            cookie.setPath("/");
            cookie.setMaxAge(14 * 24 * 60 * 60);
            response.addCookie(cookie);
        }

        return Constants.REDIRECT + "/index.jsp";
    }

    /**
     * 注销登录 清空数据
     *
     * @return 重定向login.jsp
     */
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        //1.清空session中的用户数据
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");

        //2.清空和覆盖cookies存储的自动登录信息
        Cookie cookie = new Cookie(Constants.AUTO_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        //3.转发到登录页面
        request.setAttribute("msg", "注销登录成功");
        return Constants.FORWARD + "/login.jsp";
    }
}
