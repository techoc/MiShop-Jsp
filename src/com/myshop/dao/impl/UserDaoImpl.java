package com.myshop.dao.impl;

import com.myshop.dao.UserDao;
import com.myshop.entity.User;
import com.myshop.utils.C3P0Utils;
import com.myshop.utils.Constants;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * 数据库访问实现类
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User selectUserByUsername(String username) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from user where u_name = ?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), username);
    }

    @Override
    public int insertUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "insert into user(u_name,u_password,u_sex,u_status,u_code,u_email,u_role) " +
                "value(?,?,?,?,?,?,?)";
        return queryRunner.update(
                sql,
                user.getU_name(),
                user.getU_password(),
                user.getU_sex(),
                user.getU_status(),
                user.getU_code(),
                user.getU_email(), user.getU_role()
        );
    }

    @Override
    public User selectUserByCode(String code) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from user where u_code = ?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), code);
    }

    @Override
    public int updateStatusByUid(int uid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update user set u_status = ? where u_id =?";
        return queryRunner.update(sql, Constants.USER_ACTIVE, uid);
    }
}
