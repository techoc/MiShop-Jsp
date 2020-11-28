package com.itqf.dao.impl;

import com.itqf.dao.UserDao;
import com.itqf.entity.User;
import com.itqf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * 数据库访问实现类
 */
public class UserDaoImpl implements UserDao {
    //创建queryRunner对象
    private final QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    @Override
    public User selectUserByUsername(String username) {
        String sql = "select * from user where u_name = ?";
        try {
            return queryRunner.query(sql, new BeanHandler<>(User.class), username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
