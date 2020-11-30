package com.myshop.controller;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 生成验证码的Controller
 */
@WebServlet(name = "CodeController", value = "/code")
public class CodeController extends BaseServlet {
    /**
     *生成验证码
     */
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.生成验证码对象
        ValidateCode validateCode = new ValidateCode(100, 35, 4, 10);

        //2.将验证码放入到Session中
        String code = validateCode.getCode();
        request.getSession().setAttribute("code", code);

        //3.将验证码写回页面中
        ServletOutputStream outputStream = response.getOutputStream();
        validateCode.write(outputStream);

    }
}
