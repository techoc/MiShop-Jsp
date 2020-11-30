package com.myshop.service;

import com.myshop.entity.User;

import java.sql.SQLException;

/**
 * 用户模块对应的业务逻辑接口类
 */
public interface UserService {
    /**
     * 检测用户名是否存在
     *
     * @param username 被检测的用户名
     * @return boolean true存在 false不存在
     */
    boolean checkedUser(String username) throws SQLException;

    /**
     * 注册用户
     *
     * @param user 用户对象
     * @return 插入数据影响的行数
     */
    int registerUser(User user) throws SQLException;

    /**
     * 激活用户
     *
     * @param code 激活码
     * @return 三个状态 0激活失败 1激活成功 2已经激活
     */
    int activeUser(String code) throws SQLException;

    /**
     * 用户登录
     *
     * @param username 账号
     * @param password 密码
     * @return 用户对象
     */
    User login(String username, String password) throws SQLException;
}
