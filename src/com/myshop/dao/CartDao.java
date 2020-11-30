package com.myshop.dao;

import com.myshop.entity.Cart;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface CartDao {
    /**
     * 购物车中是否存在该商品
     *
     * @param uid 用户id
     * @param pid 商品id
     */
    Cart hasCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException;

    /**
     * 修改购物车属性
     *
     * @param cart 购物车对象
     */
    void updateCart(Cart cart) throws SQLException;

    /**
     * 添加商品到购物车
     *
     * @param cart 购物车对象
     */
    void insertCart(Cart cart) throws SQLException;

    /**
     * 通过用户id查找购物车
     *
     * @param uid 用户id
     * @return cart集合
     */
    List<Cart> selectCartsByUid(int uid) throws InvocationTargetException, IllegalAccessException, SQLException;

    /**
     * 通过购物车id删除商品
     *
     * @param cid 购物车id
     */
    void deleteCartByCid(String cid) throws SQLException;

    /**
     * 修改购物车商品数量
     *
     * @param count   商品小计
     * @param cnumbig 商品个数
     * @param cid     购物车id
     */
    void updateByCid(BigDecimal count, BigDecimal cnumbig, String cid) throws SQLException;

    /**
     * 通过用户id删除购物车
     *
     * @param uid 用户id
     */
    void deleteCartByUid(String uid) throws SQLException;
}
