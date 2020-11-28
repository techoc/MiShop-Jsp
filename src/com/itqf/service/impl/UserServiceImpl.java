package com.itqf.service.impl;

import com.itqf.dao.UserDao;
import com.itqf.dao.impl.UserDaoImpl;
import com.itqf.entity.User;
import com.itqf.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public boolean checkedUser(String username) {
        User user = userDao.selectUserByUsername(username);
        return user != null;
    }
}
