package com.itqf.dao.impl;

import com.itqf.dao.CartDao;
import com.itqf.entity.Cart;
import com.itqf.entity.Product;
import com.itqf.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartDaoImpl implements CartDao {
    private final QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    @Override
    public Cart hasCart(int uid, String pid) throws SQLException, InvocationTargetException, IllegalAccessException {
        //cart --> product 连接查询 多表查询
        String sql = "select * from product p join cart c on p.p_id=c.p_id where c.u_id=? and c.p_id=?;";
        Map<String, Object> query = queryRunner.query(sql, new MapHandler(), uid, pid);
        if (query == null) {
            return null;
        }

        Cart cart = new Cart();
        Product product = new Product();

        BeanUtils.populate(cart, query);
        BeanUtils.populate(product, query);

        cart.setProduct(product);

        return cart;
    }

    @Override
    public void updateCart(Cart cart) throws SQLException {
        String sql = "update cart set c_num=?, c_count=? where c_id=?;";
        queryRunner.update(sql, cart.getC_num(), cart.getC_count(), cart.getC_id());
    }

    @Override
    public void insertCart(Cart cart) throws SQLException {
        String sql = "insert into cart (u_id,p_id,c_num,c_count) value(?,?,?,?);";

        queryRunner.update(sql, cart.getU_id(), cart.getProduct().getP_id(), cart.getC_num(), cart.getC_count());
    }

    @Override
    public List<Cart> selectCartsByUid(int uid) throws InvocationTargetException, IllegalAccessException, SQLException {
        //查询cart需要关联到商品表
        String sql = "select * from product p join cart c on p.p_id=c.p_id where c.u_id=?;";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), uid);

        if (list == null) {
            return null;
        }
        List<Cart> carts = new ArrayList<>();

        for (Map<String, Object> map :
                list) {
            Cart cart = new Cart();
            Product product = new Product();
            BeanUtils.populate(cart, map);
            BeanUtils.populate(product, map);
            cart.setProduct(product);
            carts.add(cart);
        }
        return carts;
    }

    @Override
    public void deleteCartByCid(String cid) throws SQLException {
        String sql = "delete from cart where c_id = ?";
        queryRunner.update(sql, cid);
    }

    @Override
    public void updateByCid(BigDecimal count, BigDecimal cnumbig, String cid) throws SQLException {
        String sql = "update cart set c_count=?,c_num=? where c_id=?";
        queryRunner.update(sql, count, cnumbig, cid);
    }

    @Override
    public void deleteCartByUid(String uid) throws SQLException {
        String sql = "delete from cart where u_id=?";
        queryRunner.update(sql,uid);
    }


}
