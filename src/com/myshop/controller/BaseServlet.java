package com.myshop.controller;

import com.myshop.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BaseServlet用于集中处理方法的调用
 * 以及返回值处理 默认页对应的方法
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求标识符
        String methodStr = req.getParameter(Constants.TAG);

        //如果method没有获取到值！我们就跳转到首页！
        if (methodStr == null || methodStr.equals("")) {
            methodStr = Constants.INDEX;
        }

        //2.反射调用对应的业务逻辑方法
        Class aClass = this.getClass();
        try {
            Method method = aClass.getMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);
            Object result = method.invoke(this, req, resp);

            //4.处理返回值
            //转发 forward:/路径
            //重定向 redirect:/路径
            //字符串即可{}
            //返回字节文件方法返回值类型void使用response对象手动写回
            if (result != null) {
                String str = (String) result;
                if (str.startsWith(Constants.FORWARD)) {
                    //转发
                    String path = str.substring(str.indexOf(Constants.FLAG) + 1);
                    req.getRequestDispatcher(path).forward(req, resp);
                } else if (str.startsWith(Constants.REDIRECT)) {
                    //重定向
                    String path = str.substring(str.indexOf(Constants.FLAG) + 1);
                    resp.sendRedirect(path);
                } else {
                    //字符串
                    resp.getWriter().println(str);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            //没有反射到方法
        }
    }

    /**
     * 当method的标识符没有值的时候，默认执行每个Controller的index方法
     * 默认处理： 访问index.jsp主页
     */
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        return Constants.FORWARD + "/index.jsp";
    }
}
