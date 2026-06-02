package com.pradyumn.payflow.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradyumn.payflow.dto.CreateWalletRequest;
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
    public Wallet createWallet(
            @Valid @RequestBody CreateWalletRequest request) {

        return walletService.createWallet(request);
    }
}