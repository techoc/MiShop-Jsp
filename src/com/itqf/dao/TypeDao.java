package com.itqf.dao;

import com.itqf.entity.Type;

import java.sql.SQLException;
import java.util.List;

public interface TypeDao {
    List<Type> selectAll() throws SQLException;
}
