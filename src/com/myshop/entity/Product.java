package com.myshop.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 对应数据库的商品表
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    private int p_id;           //商品的唯一主键
    private int t_id;           //类别的主键id
    private String p_name;      //商品的名称
    private Date p_time;        //商品的上市时间
    private String p_image;     //商品图片的路径
    private BigDecimal p_price; //商品的价格
    private int p_state;        //商品的热门指数
    private String p_info;         //商品的描述

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public Date getP_time() {
        return p_time;
    }

    public void setP_time(Date p_time) {
        this.p_time = p_time;
    }

    public String getP_image() {
        return p_image;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public BigDecimal getP_price() {
        return p_price;
    }

    public void setP_price(BigDecimal p_price) {
        this.p_price = p_price;
    }

    public int getP_state() {
        return p_state;
    }

    public void setP_state(int p_state) {
        this.p_state = p_state;
    }

    public String getP_info() {
        return p_info;
    }

    public void setP_info(String p_info) {
        this.p_info = p_info;
    }

    @Override
    public String toString() {
        return "Product{" +
                "p_id=" + p_id +
                ", t_id=" + t_id +
                ", p_name='" + p_name + '\'' +
                ", p_time=" + p_time +
                ", p_image='" + p_image + '\'' +
                ", p_price=" + p_price +
                ", p_state=" + p_state +
                ", p_info=" + p_info +
                '}';
    }
}
