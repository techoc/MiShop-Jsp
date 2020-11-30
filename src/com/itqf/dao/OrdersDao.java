package com.itqf.dao;

import com.itqf.entity.Item;
import com.itqf.entity.Orders;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface OrdersDao {
    /**
     * 添加订单
     *
     * @param orders 订单对象
     */
    void insertOrders(Orders orders) throws SQLException;

    /**
     * 保存订单项
     *
     * @param items 订单项对象
     */
    void insertItems(List<Item> items) throws SQLException;

    /**
     * 通过用户id查找订单项
     * @param uid
     * @return
     */
    List<Orders> selectOrdersByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException;
}
