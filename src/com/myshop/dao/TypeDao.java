package com.myshop.dao;

import com.myshop.entity.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeDao {
    List<Type> selectAll() throws SQLException;
}
