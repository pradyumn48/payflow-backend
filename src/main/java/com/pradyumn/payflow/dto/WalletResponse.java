package com.pradyumn.payflow.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletResponse {

    private Long id;

    private BigDecimal balance;

    private Long userId;

    private String userName;

    private LocalDateTime createdAt;
}