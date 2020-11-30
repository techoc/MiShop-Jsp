package com.myshop.service;

import com.myshop.entity.Orders;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface OrdersService {

    /**
     * 创建订单
     *
     * @param aid 地址id
     * @param uid 用户id
     * @param sum 总金额
     */
    void createOrder(String aid, String uid, String sum) throws IllegalAccessException, SQLException, InvocationTargetException;

    /**
     * 通过用户id查找订单
     *
     * @param uid 用户id
     * @return 订单集合
     */
    List<Orders> findOrdersByuid(int uid) throws IllegalAccessException, SQLException, InvocationTargetException;
}
