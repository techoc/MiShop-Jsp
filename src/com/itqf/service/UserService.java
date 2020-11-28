package com.itqf.service;

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
    boolean checkedUser(String username);
}
