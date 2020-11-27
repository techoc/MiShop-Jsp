package com.itqf.entity;

import java.io.Serializable;

/**
 * 对应数据库的类别表
 */
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    private int t_id;        //类别的主键id
    private String t_name;   //类别的名称
    private String t_info;   //类别的描述

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_info() {
        return t_info;
    }

    public void setT_info(String t_info) {
        this.t_info = t_info;
    }

    @Override
    public String toString() {
        return "Type{" +
                "t_id=" + t_id +
                ", t_name='" + t_name + '\'' +
                ", t_info='" + t_info + '\'' +
                '}';
    }
}
