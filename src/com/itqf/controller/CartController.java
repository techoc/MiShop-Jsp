package com.itqf.controller;

import com.itqf.entity.Cart;
import com.itqf.entity.User;
import com.itqf.service.CartService;
import com.itqf.service.impl.CartServiceImpl;
import com.itqf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends BaseServlet {
    /**
     * 添加商品到购物车
     */
    public String create(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, SQLException, InvocationTargetException {
        //1.判断是否已经登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            session.setAttribute("msg", "添加购物车请先登录");
            return Constants.REDIRECT + "/login.jsp";
        }
        //2.商品的id和用户id
        int uid = user.getU_id();
        String pid = request.getParameter("pid");

        CartService cartService = new CartServiceImpl();
        cartService.createCart(uid, pid);

        return Constants.FORWARD + "/cartSuccess.jsp";
    }

    /**
     * 展示购物车
     */
    public String show(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, SQLException, InvocationTargetException {
        //1.判断是否已经登录
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            session.setAttribute("msg", "查看购物车必须先登录");
            return Constants.FORWARD + "/login.jsp";
        }
        //2.获取参数
        int uid = user.getU_id();

        //3.电泳业务逻辑进行数据查询
        CartService cartService = new CartServiceImpl();
        List<Cart> carts = cartService.findAll(uid);
        request.setAttribute("carts", carts);

        return Constants.FORWARD + "/cart.jsp";
    }

    /**
     * 删除购物车的商品
     */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.获取cid
        String cid = request.getParameter("cid");

        //2.调用业务逻辑删除
        CartService cartService = new CartServiceImpl();
        cartService.deleteCartByCid(cid);

        //3.转发到展示的处理方法中
        return Constants.FORWARD + "/cart?method=show";

    }

    /**
     * 修改购物车商品的数量
     */
    public String update(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.获取cid
        String cid = request.getParameter("cid");
        String price = request.getParameter("price");   //商品的单价
        String cnum = request.getParameter("cnum");     //修改后的数量

        //2.调用业务逻辑删除
        CartService cartService = new CartServiceImpl();
        cartService.updateCartByCid(cid, price, cnum);

        //3.转发到展示的处理方法中
        return Constants.FORWARD + "/cart?method=show";
    }

    /**
     * 清空购物车
     */
    public String clear(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.获取uid
        String uid = request.getParameter("uid");

        //2.调用业务逻辑删除
        CartService cartService = new CartServiceImpl();
        cartService.clearCart(uid);

        //3.转发到展示的处理方法中
        return Constants.FORWARD + "/cart?method=show";
    }
}
