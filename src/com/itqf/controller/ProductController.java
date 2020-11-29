package com.itqf.controller;

import com.itqf.entity.PageBean;
import com.itqf.entity.Product;
import com.itqf.service.ProductService;
import com.itqf.service.impl.ProductServiceImpl;
import com.itqf.utils.Constants;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * 商品模块的controller
 */
@WebServlet(name = "ProductController", value = "/product")
public class ProductController extends BaseServlet {
    /**
     * 分页查找展示 类别商品
     */
    public String show(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.接受请求参数 tid类别id
        String tid = request.getParameter("tid");
        //从前端获取当前页数和页容量
        int pageSize = 8;

        String currentPage = request.getParameter("currentPage");
        int page = 1;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }

        //2.调用业务逻辑得到需要展示的PageBean
        ProductService productService = new ProductServiceImpl();
        PageBean<Product> pageBean = productService.findPage(tid, page, pageSize);
        //3.响应

        request.setAttribute("pageBean", pageBean);
        return Constants.FORWARD + "/goodsList.jsp";
    }

    public String detail(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.获取请求参数
        String pid = request.getParameter("pid");

        //2.调用业务逻辑
        ProductService productService = new ProductServiceImpl();
        Product product = productService.findProductByPid(pid);

        //3.响应
        request.setAttribute("product", product);
        return Constants.FORWARD + "/goodsDetail.jsp";
    }
}
