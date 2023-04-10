package com.example.testingworkshop.repository;

import com.example.testingworkshop.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransactionJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;
    private final TrxRowMapper trxRowMapper;

    public Transaction getById(long id) {
        return jdbcTemplate.queryForObject("select * from transactions where id = ?", new Object[] {id}, trxRowMapper);
    }


}
