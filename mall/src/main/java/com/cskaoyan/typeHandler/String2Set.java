package com.cskaoyan.typeHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * @Author: Li Qing
 * @Create: 2020/4/24 13:30
 * @Version: 1.0
 */
@MappedTypes(Set.class)
public class String2Set<T> implements TypeHandler<Set<T>> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, Set<T> set, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(set);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setString(index, s);

    }

    @Override
    public Set<T> getResult(ResultSet resultSet, String column) throws SQLException {
        return transfer(resultSet.getString(column));

    }

    @Override
    public Set<T> getResult(ResultSet resultSet, int index) throws SQLException {
        return transfer(resultSet.getString(index));
    }

    @Override
    public Set<T> getResult(CallableStatement callableStatement, int index) throws SQLException {
        return transfer(callableStatement.getString(index));
    }

    private Set<T> transfer(String result) {
        ObjectMapper objectMapper = new ObjectMapper();
        Set<T> set = null;
        try {
            set = objectMapper.readValue(result, new TypeReference<Set<T>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return set;
    }
}
