package com.myshop.service.impl;

import com.myshop.dao.TypeDao;
import com.myshop.dao.impl.TypeDaoImpl;
import com.myshop.entity.Type;
import com.myshop.service.TypeService;

import java.sql.SQLException;
import java.util.List;

public class TypeServiceImpl implements TypeService {
    private final TypeDao typeDao = new TypeDaoImpl();

    @Override
    public List<Type> findAll() throws SQLException {
        List<Type> types = typeDao.selectAll();
        return types;
    }
}
