package com.pradyumn.payflow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderWalletId;

    private Long receiverWalletId;

    private BigDecimal amount;

    private String status;

    @Column(unique = true)
    private String transactionReference;

    private LocalDateTime createdAt;
}