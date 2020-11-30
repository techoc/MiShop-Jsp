package com.myshop.service;

import com.myshop.entity.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressService {
    /**
     * 通过用户id查找用户地址
     *
     * @param uid 用户id
     * @return 地址集合
     */
    List<Address> findAddressByUid(int uid) throws SQLException;

    /**
     * 添加地址数据
     *
     * @param address 地址对象
     */
    void saveAddress(Address address) throws SQLException;

    /**
     * 通过地址id删除地址
     *
     * @param aid 地址id
     */
    void deleteAddressByAid(String aid) throws SQLException;

    /**
     * 设置地址为默认地址
     *
     * @param aid 地址id
     * @param uid
     */
    void setAddressToDefault(String aid, int uid) throws SQLException;

    /**
     * 修改地址
     *
     * @param address   地址对象
     */
    void updateByAid(Address address) throws SQLException;
}
