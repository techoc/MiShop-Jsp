package com.itqf.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 对饮数据库的订单表
 */
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

    private String o_id;    //订单编号是字符串类型但是也是唯一标识
    private int u_id;       //用户实体的主键属性
    private int a_id;       //地址实体的唯一主键列
    private BigDecimal o_count; //订单的总金额
    private Date o_time;        //订单的详细时间
    private int o_state;        //订单状态 0 未付款，1已经付款未发货 2发货待收货 3 收货待评价 4订单完成 5 退货状态

    public String getO_id() {
        return o_id;
    }

    public void setO_id(String o_id) {
        this.o_id = o_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public BigDecimal getO_count() {
        return o_count;
    }

    public void setO_count(BigDecimal o_count) {
        this.o_count = o_count;
    }

    public Date getO_time() {
        return o_time;
    }

    public void setO_time(Date o_time) {
        this.o_time = o_time;
    }

    public int getO_state() {
        return o_state;
    }

    public void setO_state(int o_state) {
        this.o_state = o_state;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "o_id='" + o_id + '\'' +
                ", u_id=" + u_id +
                ", a_id=" + a_id +
                ", o_count=" + o_count +
                ", o_time=" + o_time +
                ", o_state=" + o_state +
                '}';
    }
}
