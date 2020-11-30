package com.myshop.service.impl;

import com.myshop.dao.ProductDao;
import com.myshop.dao.impl.ProductDaoImpl;
import com.myshop.entity.PageBean;
import com.myshop.entity.Product;
import com.myshop.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public PageBean<Product> findPage(String tid, int page, int pageSize) throws SQLException {
        long count = productDao.selectCountByTid(tid);  //查询总条数

        List<Product> list = productDao.selectProductByPage(page, pageSize, tid);  //查询当前页对应的商品


        return new PageBean<Product>(list, page, pageSize, count);
    }

    @Override
    public Product findProductByPid(String pid) throws SQLException {
        Product product = productDao.selectProductByPid(pid);
        return product;
    }
}
