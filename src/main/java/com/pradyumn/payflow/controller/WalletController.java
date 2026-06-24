package com.pradyumn.payflow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradyumn.payflow.dto.CreateWalletRequest;
import com.pradyumn.payflow.dto.DepositRequest;
import com.pradyumn.payflow.dto.WalletResponse;
import com.pradyumn.payflow.entity.Wallet;
import com.pradyumn.payflow.service.WalletService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public WalletResponse createWallet(
            @Valid @RequestBody CreateWalletRequest request) {

        return walletService.createWallet(request);
    }

    @GetMapping("/{id}")
    public WalletResponse getWalletById(
            @PathVariable Long id) {

        return walletService.getWalletById(id);
    }

    @PostMapping("/{id}/deposit")
    public Wallet depositMoney(
            @PathVariable Long id,
            @Valid @RequestBody DepositRequest request) {

        return walletService.depositMoney(
                id,
                request.getAmount());
    }
}