package com.itqf.service.impl;

import com.itqf.dao.UserDao;
import com.itqf.dao.impl.UserDaoImpl;
import com.itqf.entity.User;
import com.itqf.service.UserService;
import com.itqf.utils.Constants;
import com.itqf.utils.EmailUtils;
import com.itqf.utils.MD5Utils;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean checkedUser(String username) throws SQLException {
        User user = userDao.selectUserByUsername(username);
        return user != null;
    }

    @Override
    public int registerUser(User user) throws SQLException {
        //1.用户保存到数据库
        int result = userDao.insertUser(user);

        //2.发送激活邮件
        EmailUtils.sendEmail(user);

        return result;
    }

    @Override
    public int activeUser(String code) throws SQLException {
        //1.根据激活码查找用户
        User user = userDao.selectUserByCode(code);
        if (user == null) {
            return Constants.ACTIVE_FAIL;   //激活失败
        }
        //2.判断用户是否激活
        if (user.getU_status().equals(Constants.USER_ACTIVE)) {
            return Constants.ACTIVE_ALREADY;   //已经激活过了
        }
        //3.进行激活操作
        int i = userDao.updateStatusByUid(user.getU_id());
        if (i > 0) {
            return Constants.ACTIVE_SUCCESS;   //激活成功
        }
        return Constants.ACTIVE_FAIL;
    }

    @Override
    public User login(String username, String password) throws SQLException {
        //1.需要将密码用MD5处理
        String md5password = MD5Utils.md5(password);

        //2.根据用户名查找用户
        User user = userDao.selectUserByUsername(username);
        if (user != null && user.getU_password().equals(md5password)) {
            return user;
        }
        return null;
    }
}
