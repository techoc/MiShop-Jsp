package com.itqf.dao;

import com.itqf.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    /**
     * 查询数据库中该类别的商品的总条数
     *
     * @param tid 类别id
     * @return 总条数
     */
    long selectCountByTid(String tid) throws SQLException;

    /**
     * Limit (currentPage-1)*pageSize,pageSize; 实现分页查找商品
     *
     * @param page     当前页
     * @param pageSize 页大小
     * @param tid      类别id
     * @return product的list集合
     */
    List<Product> selectProductByPage(Integer page, int pageSize, String  tid) throws SQLException;

    /**
     * 通过商品id查找商品
     * @param pid   商品id
     * @return  product对象
     */
    Product selectProductByPid(String pid) throws SQLException;
}
