package com.pradyumn.payflow.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.pradyumn.payflow.dto.CreateWalletRequest;
import com.pradyumn.payflow.dto.WalletResponse;
import com.pradyumn.payflow.entity.User;
import com.pradyumn.payflow.entity.Wallet;
import com.pradyumn.payflow.exception.WalletAlreadyExistsException;
import com.pradyumn.payflow.exception.WalletNotFoundException;
import com.pradyumn.payflow.repository.UserRepository;
import com.pradyumn.payflow.repository.WalletRepository;

import jakarta.transaction.Transactional;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletService(
            WalletRepository walletRepository,
            UserRepository userRepository) {

        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    public WalletResponse createWallet(
            CreateWalletRequest request) {

        if (walletRepository.existsByUserId(
                request.getUserId())) {

            throw new WalletAlreadyExistsException(
                    "Wallet already exists for this user");
        }

        User user = userRepository.findById(
                request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));

        Wallet wallet = new Wallet();

        wallet.setUser(user);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setCreatedAt(LocalDateTime.now());

        Wallet savedWallet =
                walletRepository.save(wallet);

        WalletResponse response =
                new WalletResponse();

        response.setWalletId(
                savedWallet.getId());

        response.setUserId(
                user.getId());

        response.setUserName(
                user.getName());

        response.setBalance(
                savedWallet.getBalance());

        response.setCreatedAt(
                savedWallet.getCreatedAt());

        return response;
    }

    public WalletResponse getWalletById(
            Long walletId) {

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() ->
                        new WalletNotFoundException(
                                "Wallet not found"));

        WalletResponse response =
                new WalletResponse();

        response.setWalletId(
                wallet.getId());

        response.setUserId(
                wallet.getUser().getId());

        response.setUserName(
                wallet.getUser().getName());

        response.setBalance(
                wallet.getBalance());

        response.setCreatedAt(
                wallet.getCreatedAt());

        return response;
    }

    @Transactional
    public Wallet depositMoney(
            Long walletId,
            BigDecimal amount) {

        Wallet wallet = walletRepository.findById(
                walletId)
                .orElseThrow(() ->
                        new WalletNotFoundException(
                                "Wallet not found"));

        wallet.setBalance(
                wallet.getBalance().add(amount));

        return walletRepository.save(wallet);
    }
}