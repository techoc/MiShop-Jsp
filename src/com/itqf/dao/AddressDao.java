package com.itqf.dao;

import com.itqf.entity.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
    /**
     * 通过用户id查找地址
     *
     * @param uid 用户id
     * @return 地址集合
     */
    List<Address> selectAddressByUid(int uid) throws SQLException;

    /**
     * 添加地址
     *
     * @param address 地址对象
     */
    void insertAddress(Address address) throws SQLException;

    /**
     * 删除地址
     *
     * @param aid 地址id
     */
    void deleteAddressByAid(String aid) throws SQLException;

    /**
     * 将aid的地址设置为默认地址
     *
     * @param aid 地址ia
     */
    void updateAddressToDefault(String aid) throws SQLException;

    /**
     * 将非aid的地址全部设置为非默认地址
     *
     * @param aid 默认地址id
     * @param uid 用户id
     */
    void updateAddressToCommens(String aid, int uid) throws SQLException;

    /**
     * 修改地址
     *
     * @param address   地址对象
     */
    void updateAddressByAid(Address address) throws SQLException;
}
