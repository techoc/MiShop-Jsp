package com.itqf.dao;

import com.itqf.entity.User;

/**
 * 负责用户模块数据库访问接口
 */
public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param username  被查询的用户名
     * @return  返回对应的用户数据
     */
    User selectUserByUsername(String username);
}
