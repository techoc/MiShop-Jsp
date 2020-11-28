package com.itqf.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "ProductController", value = "/product")
public class ProductController extends BaseServlet {

    public String show(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.getWriter().println("商品模块的展示");
        return "redirect:/user?method=login";
    }

}
