package com.itqf.service;

import com.itqf.entity.PageBean;
import com.itqf.entity.Product;

import java.sql.SQLException;

public interface ProductService {
    /**
     * 商品的分页查找
     * @param tid   类别id
     * @param page  前端的当前页
     * @param pageSize  页大小
     * @return  PageBean对象
     */
    PageBean<Product> findPage(String tid, int page, int pageSize) throws SQLException;

    /**
     * 通过商品id查找商品
     * @param pid 商品id
     * @return product对象
     */
    Product findProductByPid(String pid) throws SQLException;
}
