package com.myshop.service.impl;

import com.myshop.dao.CartDao;
import com.myshop.dao.ProductDao;
import com.myshop.dao.impl.CartDaoImpl;
import com.myshop.dao.impl.ProductDaoImpl;
import com.myshop.entity.Cart;
import com.myshop.entity.Product;
import com.myshop.service.CartService;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class CartServiceImpl implements CartService {
    private final CartDao cartDao = new CartDaoImpl();

    @Override
    public void createCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException {
        //1.判断商品是否已经存在购物车里
        Cart cart = cartDao.hasCart(uid, pid);

        if (cart != null) {
            //添加过购物车！ 修改数量
            cart.setC_num(cart.getC_num() + 1);
            cartDao.updateCart(cart);
        } else {
            //不存在则添加
            //1.根据商品id查询商品
            ProductDao productDao = new ProductDaoImpl();
            Product product = productDao.selectProductByPid(pid);

            cart = new Cart();
            cart.setC_num(1);
            cart.setP_id(Integer.parseInt(pid));
            cart.setProduct(product);
            cart.setU_id(uid);

            cartDao.insertCart(cart);

        }

    }

    @Override
    public List<Cart> findAll(int uid) throws IllegalAccessException, SQLException, InvocationTargetException {
        List<Cart> carts =  cartDao.selectCartsByUid(uid);
        return carts;
    }

    @Override
    public void deleteCartByCid(String cid) throws SQLException {
        cartDao.deleteCartByCid(cid);
    }

    @Override
    public void updateCartByCid(String cid, String price, String cnum) throws SQLException {
        BigDecimal cnumbig = new BigDecimal(cnum);
        BigDecimal pricebig = new BigDecimal(price);

        BigDecimal count = pricebig.multiply(cnumbig);

        cartDao.updateByCid(count,cnumbig,cid);
    }

    @Override
    public void clearCart(String uid) throws SQLException {
        cartDao.deleteCartByUid(uid);
    }
}
