package com.example.testingworkshop.repository;

import com.example.testingworkshop.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRawJdbcRepository {

    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public List<Transaction> getAll() {
        List<Transaction> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", username, password)) {
            PreparedStatement statement = connection.prepareStatement("select * from transactions");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.add(
                    new Transaction()
                        .setId( rs.getLong("id"))
                        .setAmount(rs.getBigDecimal("amount"))
                        .setCategory(rs.getString("category"))
                        .setDate(rs.getTimestamp("date").toInstant())
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while get all transactions", e);
        }
        return result;
    }

}
