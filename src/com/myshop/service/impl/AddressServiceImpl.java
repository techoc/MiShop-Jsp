package com.myshop.service.impl;


import com.myshop.dao.AddressDao;
import com.myshop.dao.impl.AddressDaoImpl;
import com.myshop.entity.Address;
import com.myshop.service.AddressService;

import java.sql.SQLException;
import java.util.List;

public class AddressServiceImpl implements AddressService {
    private final AddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<Address> findAddressByUid(int uid) throws SQLException {
        List<Address> addresses = addressDao.selectAddressByUid(uid);
        return addresses;
    }

    @Override
    public void saveAddress(Address address) throws SQLException {
        addressDao.insertAddress(address);
    }

    @Override
    public void deleteAddressByAid(String aid) throws SQLException {
        addressDao.deleteAddressByAid(aid);
    }

    @Override
    public void setAddressToDefault(String aid, int uid) throws SQLException {
        //1.将aid状态改为1   默认地址
        addressDao.updateAddressToDefault(aid);

        //2.将非aid状态改为0  非默认地址
        addressDao.updateAddressToCommens(aid, uid);
    }

    @Override
    public void updateByAid(Address address) throws SQLException {
        addressDao.updateAddressByAid(address);
    }
}
