package com.itqf.service.impl;

import com.itqf.dao.TypeDao;
import com.itqf.dao.impl.TypeDaoImpl;
import com.itqf.entity.Type;
import com.itqf.service.TypeService;

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
