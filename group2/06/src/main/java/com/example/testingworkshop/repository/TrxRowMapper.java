package com.example.testingworkshop.repository;

import com.example.testingworkshop.model.Transaction;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TrxRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Transaction()
            .setId(rs.getLong("id"))
            .setAmount(rs.getBigDecimal("amount"))
            .setDate(rs.getTimestamp("date").toInstant())
            .setCategory(rs.getString("category"));
    }
}
