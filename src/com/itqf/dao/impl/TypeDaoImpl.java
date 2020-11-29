package com.itqf.dao.impl;

import com.itqf.dao.TypeDao;
import com.itqf.entity.Type;
import com.itqf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class TypeDaoImpl implements TypeDao {
    private final QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    @Override
    public List<Type> selectAll() throws SQLException {
        String sql = "select * from type limit 5;";
        return queryRunner.query(sql, new BeanListHandler<>(Type.class));
    }
}
