package com.itqf.entity;

import java.io.Serializable;

/**
 * 对应数据库的用户表
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int uid;            //用户实体的主键属性
    private String username;    //对应数据库的u_name字段 用户账户
    private String upassword;   //用户密码
    private String email;       //对应数据库的uemail字段 用户邮箱
    private String usex;        //用户性别
    private String ustatus;     //用户的激活状态 0 未激活 1 激活
    private String code;        //邮件激活码
    private int urole;          //用户 0 管理员 1

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    public int getUrole() {
        return urole;
    }

    public void setUrole(int urole) {
        this.urole = urole;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", upassword='" + upassword + '\'' +
                ", email='" + email + '\'' +
                ", usex='" + usex + '\'' +
                ", ustatus='" + ustatus + '\'' +
                ", code='" + code + '\'' +
                ", urole=" + urole +
                '}';
    }
}
