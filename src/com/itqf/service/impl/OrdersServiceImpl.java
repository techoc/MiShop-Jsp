package com.itqf.service.impl;

import com.itqf.dao.CartDao;
import com.itqf.dao.OrdersDao;
import com.itqf.dao.impl.CartDaoImpl;
import com.itqf.dao.impl.OrdersDaoImpl;
import com.itqf.entity.Cart;
import com.itqf.entity.Item;
import com.itqf.entity.Orders;
import com.itqf.service.OrdersService;
import com.itqf.utils.RandomUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersServiceImpl implements OrdersService {
    @Override
    public void createOrder(String aid, String uid, String sum) throws IllegalAccessException, SQLException, InvocationTargetException {
        //1.创建一个订单对象进行保存
        Orders orders = new Orders();
        BigDecimal bigDecimal = new BigDecimal(sum);

        String orderId = RandomUtils.createOrderId();
        orders.setO_id(orderId);
        orders.setA_id(Integer.parseInt(aid));
        orders.setU_id(Integer.parseInt(uid));
        orders.setO_time(new Date());
        orders.setO_state(1);   //未支付
        orders.setO_count(bigDecimal);

        //2.保存订单项
        OrdersDao ordersDao = new OrdersDaoImpl();
        ordersDao.insertOrders(orders);

        //3.将购物车转成订单项Item
        CartDao cartDao = new CartDaoImpl();
        List<Cart> carts = cartDao.selectCartsByUid(Integer.parseInt(uid));


        List<Item> items = new ArrayList<>();
        for (Cart cart :
                carts) {
            Item item = new Item();
            item.setO_id(orderId);
            item.setP_id(cart.getP_id());
            item.setI_num(cart.getC_num());
            item.setI_count(cart.getC_count());
            items.add(item);
        }

        //4.保存订单对应的订单项
        ordersDao.insertItems(items);

        //5.清空购物车
        cartDao.deleteCartByUid(uid);
    }

    @Override
    public List<Orders> findOrdersByuid(int uid) throws IllegalAccessException, SQLException, InvocationTargetException {
        OrdersDao ordersDao = new OrdersDaoImpl();

        List<Orders> ordersList = ordersDao.selectOrdersByUid(uid);

        return ordersList;
    }
}
