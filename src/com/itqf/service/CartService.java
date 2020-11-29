package com.itqf.service;

import com.itqf.entity.Cart;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface CartService {
    /**
     * 添加商品到购物车
     *
     * @param uid 用户id
     * @param pid 商品id
     */
    void createCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException;

    /**
     * 查询该用户所有的购物车数据
     *
     * @param uid 用户id
     * @return Cart集合
     */
    List<Cart> findAll(int uid) throws IllegalAccessException, SQLException, InvocationTargetException;

    /**
     * 通过购物车id删除商品
     *
     * @param cid 购物车id
     */
    void deleteCartByCid(String cid) throws SQLException;

    /**
     * 修改购物车中商品的数量
     * @param cid   购物车id
     * @param price 商品的价格
     * @param cnum  商品的数量
     */
    void updateCartByCid(String cid, String price, String cnum) throws SQLException;

    /**
     * 清空购物车
     * @param uid   用户id
     */
    void clearCart(String uid) throws SQLException;
}
