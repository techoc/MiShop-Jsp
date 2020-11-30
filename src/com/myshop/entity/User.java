package com.myshop.entity;

import java.io.Serializable;

/**
 * 对应数据库的用户表
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int u_id;            //用户实体的主键属性
    private String u_name;    //对应数据库的u_name字段 用户账户
    private String u_password;   //用户密码
    private String u_email;       //对应数据库的uemail字段 用户邮箱
    private String u_sex;        //用户性别
    private String u_status;     //用户的激活状态 0 未激活 1 激活
    private String u_code;        //邮件激活码
    private int u_role;          //用户 0 管理员 1

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_sex() {
        return u_sex;
    }

    public void setU_sex(String u_sex) {
        this.u_sex = u_sex;
    }

    public String getU_status() {
        return u_status;
    }

    public void setU_status(String u_status) {
        this.u_status = u_status;
    }

    public String getU_code() {
        return u_code;
    }

    public void setU_code(String u_code) {
        this.u_code = u_code;
    }

    public int getU_role() {
        return u_role;
    }

    public void setU_role(int u_role) {
        this.u_role = u_role;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", u_name='" + u_name + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_email='" + u_email + '\'' +
                ", u_sex='" + u_sex + '\'' +
                ", u_status='" + u_status + '\'' +
                ", u_code='" + u_code + '\'' +
                ", u_role=" + u_role +
                '}';
    }
}
