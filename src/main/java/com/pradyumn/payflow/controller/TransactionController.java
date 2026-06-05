package com.pradyumn.payflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradyumn.payflow.dto.CreateTransferRequest;
import com.pradyumn.payflow.dto.TransactionResponse;
import com.pradyumn.payflow.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(
            TransactionService transactionService) {

        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public TransactionResponse transferMoney(
            @Valid @RequestBody CreateTransferRequest request) {

        return transactionService.transferMoney(request);
    }

    @GetMapping("/wallet/{walletId}")
    public List<TransactionResponse> getTransactionHistory(
            @PathVariable Long walletId) {

        return transactionService
                .getTransactionHistory(walletId);
    }
}