package com.example.testingworkshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;

@ToString
@Getter
@Setter
@Accessors(chain = true)
@Table("transactions")
public class Transaction {

    @Id
    private Long id;
    private String category;
    private BigDecimal amount;
    private Instant date;

}
