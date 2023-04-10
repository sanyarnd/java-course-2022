package com.example.testingworkshop.controller;

import com.example.testingworkshop.model.Transaction;
import com.example.testingworkshop.repository.TransactionJdbcRepository;
import com.example.testingworkshop.repository.TransactionJdbcTemplateRepository;
import com.example.testingworkshop.repository.TransactionRawJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionJdbcRepository transactionJdbcRepository;
    private final TransactionRawJdbcRepository transactionRawJdbcRepository;
    private final TransactionJdbcTemplateRepository transactionJdbcTemplateRepository;

    @PostMapping
    public Transaction save(@RequestBody Transaction transaction) {
        return transactionJdbcRepository.save(transaction);
    }

    @GetMapping
    public List<Transaction> getAll() {
        return transactionRawJdbcRepository.getAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Transaction getOne(@PathVariable("id") long trxId) {
        return transactionJdbcTemplateRepository.getById(trxId);

    }

}
