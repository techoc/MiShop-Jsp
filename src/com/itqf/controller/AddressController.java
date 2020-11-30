package com.itqf.controller;

import com.itqf.entity.Address;
import com.itqf.entity.User;
import com.itqf.service.AddressService;
import com.itqf.service.impl.AddressServiceImpl;
import com.itqf.utils.Constants;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AddressController", value = "/address")
public class AddressController extends BaseServlet {

    /**
     * 展示地址
     */
    public String show(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            request.setAttribute("msg", "请先登录！");
            return Constants.REDIRECT + "login.jsp";
        }

        int uid = user.getU_id();
        AddressService addressService = new AddressServiceImpl();
        List<Address> addresses = addressService.findAddressByUid(uid);

        request.setAttribute("addresses", addresses);

        return Constants.FORWARD + "/self_info.jsp";
    }

    /**
     * 添加地址
     */
    public String add(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, SQLException {
        //1.获取请求参数
        Map<String, String[]> map = request.getParameterMap();
        Address address = new Address();
        BeanUtils.populate(address, map);

        //2.调用业务逻辑进行地址添加
        AddressService addressService = new AddressServiceImpl();
        addressService.saveAddress(address);

        //3.转发到展示的方法

        return Constants.FORWARD + "/address?method=show";

    }

    /**
     * 删除地址
     */
    public String delete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.获取请求参数
        String aid = request.getParameter("aid");

        //2.调用业务逻辑进行地址添加
        AddressService addressService = new AddressServiceImpl();
        addressService.deleteAddressByAid(aid);

        //3.转发到展示的方法
        return Constants.FORWARD + "/address?method=show";
    }

    /**
     * 设为默认地址
     */
    public String setDefault(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        //1.获取请求参数
        String aid = request.getParameter("aid");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            request.setAttribute("msg", "请先登录！");
            return Constants.REDIRECT + "login.jsp";
        }

        //2.调用业务逻辑进行地址添加
        AddressService addressService = new AddressServiceImpl();
        addressService.setAddressToDefault(aid, user.getU_id());

        //3.转发到展示的方法
        return Constants.FORWARD + "/address?method=show";
    }

    /**
     * 修改地址
     */
    public String update(HttpServletRequest request, HttpServletResponse response) throws SQLException, InvocationTargetException, IllegalAccessException {
        //1.获取请求参数
        Map<String, String[]> map = request.getParameterMap();
        Address address = new Address();
        BeanUtils.populate(address, map);

        //2.调用业务逻辑进行地址添加
        AddressService addressService = new AddressServiceImpl();
        addressService.updateByAid(address);

        //3.转发到展示的方法
        return Constants.FORWARD + "/address?method=show";
    }

}
