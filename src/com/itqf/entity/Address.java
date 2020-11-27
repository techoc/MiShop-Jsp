package com.itqf.entity;

import java.io.Serializable;

/**
 * 对应数据库的地址表
 */

public class Address implements Serializable {//序列化
    private static final long serialVersionUID = 1L;

    private int a_id;        //地址实体的唯一主键列
    private int u_id;        //用户实体的主键属性
    private String a_name;   //收件人名称
    private String a_phone;  //收件人电话
    private String a_detial; //收件人详细地址
    private int a_state;     //收件地址状态 0非默认 1默认地址

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_phone() {
        return a_phone;
    }

    public void setA_phone(String a_phone) {
        this.a_phone = a_phone;
    }

    public String getA_detial() {
        return a_detial;
    }

    public void setA_detial(String a_detial) {
        this.a_detial = a_detial;
    }

    public int getA_state() {
        return a_state;
    }

    public void setA_state(int a_state) {
        this.a_state = a_state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "a_id=" + a_id +
                ", u_id=" + u_id +
                ", a_name='" + a_name + '\'' +
                ", a_phone='" + a_phone + '\'' +
                ", a_detial='" + a_detial + '\'' +
                ", a_state=" + a_state +
                '}';
    }
}
