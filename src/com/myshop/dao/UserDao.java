package com.myshop.dao;

import com.myshop.entity.User;

import java.sql.SQLException;

/**
 * 负责用户模块数据库访问接口
 */
public interface UserDao {
    /**
     * 根据用户名查询用户
     *
     * @param username 被查询的用户名
     * @return 返回对应的用户数据
     */
    User selectUserByUsername(String username) throws SQLException;

    /**
     * 插入用户
     *
     * @param user 用户对象
     * @return 受影响的行数
     */
    int insertUser(User user) throws SQLException;

    /**
     * 根据激活码查找用户
     *
     * @param code 激活码
     * @return 用户对象
     */
    User selectUserByCode(String code) throws SQLException;

    /**
     * 根据用户ID更新用户信息
     *
     * @param uid 用户ID
     * @return 受影响的行数
     */
    int updateStatusByUid(int uid) throws SQLException;

}
