package com.myshop.dao.impl;

import com.myshop.dao.OrdersDao;
import com.myshop.entity.Address;
import com.myshop.entity.Item;
import com.myshop.entity.Orders;
import com.myshop.utils.C3P0Utils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrdersDaoImpl implements OrdersDao {
    @Override
    public void insertOrders(Orders orders) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

        String sql = "insert into orders (o_id,a_id,u_id,o_count,o_state,o_time) value (?,?,?,?,?,?)";
        queryRunner.update(sql, orders.getO_id(), orders.getA_id(), orders.getU_id(), orders.getO_count(), orders.getO_state(), orders.getO_time());

    }

    @Override
    public void insertItems(List<Item> items) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        Object[][] params = new Object[items.size()][];
        String sql = "insert into item(o_id,p_id,i_count,i_num) value (?,?,?,?)";
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            params[i] = new Object[]{item.getO_id(), item.getP_id(), item.getI_count(), item.getI_num()};

        }
        queryRunner.batch(sql, params);
    }

    @Override
    public List<Orders> selectOrdersByUid(int uid) throws SQLException, InvocationTargetException, IllegalAccessException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from address a join orders o on a.a_id=o.a_id where o.u_id=?";
        List<Map<String, Object>> list = queryRunner.query(sql, new MapListHandler(), uid);
        if (list == null) {
            return null;
        }

        List<Orders> ordersList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Orders orders = new Orders();
            Address address = new Address();

            BeanUtils.populate(orders, map);
            BeanUtils.populate(address, map);
            orders.setAddress(address);

            ordersList.add(orders);
        }

        return ordersList;
    }
}
