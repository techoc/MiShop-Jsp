package com.itqf.controller;

import com.itqf.entity.Address;
import com.itqf.entity.Cart;
import com.itqf.entity.Orders;
import com.itqf.entity.User;
import com.itqf.service.AddressService;
import com.itqf.service.CartService;
import com.itqf.service.OrdersService;
import com.itqf.service.impl.AddressServiceImpl;
import com.itqf.service.impl.CartServiceImpl;
import com.itqf.service.impl.OrdersServiceImpl;
import com.itqf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "OrdersController", value = "/orders")
public class OrdersController extends BaseServlet {
    /**
     * 结算购物车
     */
    public String preView(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        //1.获取请求参数
        String uid = request.getParameter("uid");

        //2.调用业务逻辑
        AddressService addressService = new AddressServiceImpl();
        List<Address> addressList = addressService.findAddressByUid(Integer.parseInt(uid));

        CartService cartService = new CartServiceImpl();
        List<Cart> cartList = cartService.findAll(Integer.parseInt(uid));

        //3.放入共享域
        request.setAttribute("addressList", addressList);
        request.setAttribute("cartList", cartList);


        return Constants.FORWARD + "/order.jsp";
    }

    /**
     * 创建订单
     */
    public String create(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, SQLException, InvocationTargetException {
        //1.获取请求参数
        String aid = request.getParameter("aid");
        String uid = request.getParameter("uid");
        String sum = request.getParameter("sum");

        //2.调用业务逻辑生成订单
        OrdersService ordersService = new OrdersServiceImpl();
        ordersService.createOrder(aid, uid, sum);

        //3.转发到订单展示的方法

        return Constants.FORWARD + "orders?method=show";
    }

    public String show(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, SQLException, InvocationTargetException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            request.setAttribute("msg", "请登录后查看订单");
            return Constants.FORWARD + "/login.jsp";
        }
        OrdersService ordersService = new OrdersServiceImpl();
        List<Orders> ordersList = ordersService.findOrdersByuid(user.getU_id());
        request.setAttribute("ordersList", ordersList);

        return Constants.FORWARD + "/orderList.jsp";
    }
}
