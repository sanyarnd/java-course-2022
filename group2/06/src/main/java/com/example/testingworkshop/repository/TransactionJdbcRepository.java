package com.example.testingworkshop.repository;

import com.example.testingworkshop.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionJdbcRepository extends CrudRepository<Transaction, Long> {

}
