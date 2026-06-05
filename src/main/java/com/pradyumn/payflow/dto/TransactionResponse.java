package com.pradyumn.payflow.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse {

    private Long transactionId;

    private Long senderWalletId;

    private Long receiverWalletId;

    private BigDecimal amount;

    private String status;

    private String transactionReference;

    private LocalDateTime createdAt;
}