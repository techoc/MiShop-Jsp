package com.itqf.dao.impl;

import com.itqf.dao.ProductDao;
import com.itqf.entity.Product;
import com.itqf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private final QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    @Override
    public long selectCountByTid(String tid) throws SQLException {
        String sql = "select count(1) from product where t_id = ?;";
        Object result = queryRunner.query(sql, new ScalarHandler(), tid);
        Long total = (Long) result;

        return total;
    }

    @Override
    public List<Product> selectProductByPage(Integer page, int pageSize, String tid) throws SQLException {
        String sql = "select * from product where t_id=? limit ?,?;";
        List<Product> products = queryRunner.query(sql, new BeanListHandler<>(Product.class), tid, ((page - 1) * pageSize), pageSize);
        return products;
    }

    @Override
    public Product selectProductByPid(String pid) throws SQLException {
        String sql = "select * from product where p_id = ?";
        Product product = queryRunner.query(sql, new BeanHandler<>(Product.class), pid);
        return product;
    }
}
