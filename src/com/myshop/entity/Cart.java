package com.myshop.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 对应数据库的购物车表
 */

public class Cart implements Serializable {//实现序列化
    private static final long serialVersionUID = 1L;

    private int c_id;    //购物车的唯一标识
    private int u_id;    //用户实体的主键属性
    private int p_id;    //商品的唯一主键
    private Product product;
    private BigDecimal c_count; //购物车小计
    private int c_num = 0;   //购物车商品数量

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public BigDecimal getC_count() {
        BigDecimal price = product.getP_price();
        BigDecimal bigDecimal = new BigDecimal(c_num);

        return price.multiply(bigDecimal);
    }

    public void setC_count(BigDecimal c_count) {
        this.c_count = c_count;
    }

    public int getC_num() {
        return c_num;
    }

    public void setC_num(int c_num) {
        this.c_num = c_num;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "c_id=" + c_id +
                ", u_id=" + u_id +
                ", p_id=" + p_id +
                ", c_count=" + c_count +
                ", c_num=" + c_num +
                '}';
    }
}
