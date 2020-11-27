package com.itqf.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 对应数据库的订单项
 */
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    private int i_id;           //订单项的唯一标识
    private String o_id;            //订单编号是字符串类型但是也是唯一标识
    private int p_id;               //商品的唯一主键
    private BigDecimal i_count;     //订单项的小计
    private int i_num;              //订单项的数量

    public int getI_id() {
        return i_id;
    }

    public void setI_id(int i_id) {
        this.i_id = i_id;
    }

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public BigDecimal getI_count() {
        return i_count;
    }

    public void setI_count(BigDecimal i_count) {
        this.i_count = i_count;
    }

    public int getI_num() {
        return i_num;
    }

    public void setI_num(int i_num) {
        this.i_num = i_num;
    }

    @Override
    public String toString() {
        return "Item{" +
                "i_id=" + i_id +
                ", o_id='" + o_id + '\'' +
                ", p_id=" + p_id +
                ", i_count=" + i_count +
                ", i_num=" + i_num +
                '}';
    }
}
