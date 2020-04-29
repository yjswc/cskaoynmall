package com.cskaoyan.typeHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Li Qing
 * @Create: 2020/4/20 12:56
 * @Version: 1.0
 */
@MappedTypes(List.class)
public class String2ArrayTypeHandler<T> implements TypeHandler<List<T>> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, List<T> strings, JdbcType jdbcType) throws SQLException {
        ObjectMapper objectMapper = new ObjectMapper();
        String s = null;
        try {
            s = objectMapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        preparedStatement.setString(index, s);
    }

    @Override
    public List<T> getResult(ResultSet resultSet, String column) throws SQLException {
        String result = resultSet.getString(column);
        return transfer(result);
    }

    @Override
    public List<T> getResult(ResultSet resultSet, int index) throws SQLException {
        String result = resultSet.getString(index);
        return transfer(result);
    }

    @Override
    public List<T> getResult(CallableStatement callableStatement, int index) throws SQLException {
        String result = callableStatement.getString(index);
        return transfer(result);
    }

    private List<T> transfer(String result) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<T> strings = new ArrayList<>();
        //增加判空，防止报错
        if (!StringUtils.isEmpty(result)) {
            try {
                strings = objectMapper.readValue(result, new TypeReference<List<T>>() {
                });
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return strings;
    }
}
