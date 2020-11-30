package com.myshop.utils;

/**
 * 项目的常量类
 */
public class Constants {
    /**
     * BaseServlet模块
     */
    public static final String TAG = "method";
    public static final String FORWARD = "forward:";
    public static final String REDIRECT = "redirect:";
    public static final String FLAG = ":";
    public static final String INDEX = "index";

    /**
     * 定义用户模块的常量
     */
    public static final String HAS_USER = "1";
    public static final String NOT_HAS_USER = "0";
    public static final String USER_ACTIVE = "1";
    public static final String USER_NOT_ACTIVE = "0";
    public static final int ROLE_CUSTOMER = 0;
    public static final int ROLE_ADMIN = 1;

    /**
     * 用户模块激活状态
     */
    public static final int ACTIVE_FAIL = 0;
    public static final int ACTIVE_SUCCESS = 1;
    public static final int ACTIVE_ALREADY = 2;

    /**
     * 自动登录cookie
     */
    public static final String AUTO_NAME = "autoUser";


}
