package com.pradyumn.payflow.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradyumn.payflow.dto.CreateTransferRequest;
import com.pradyumn.payflow.dto.TransactionResponse;
import com.pradyumn.payflow.entity.Transaction;
import com.pradyumn.payflow.entity.Wallet;
import com.pradyumn.payflow.exception.InsufficientBalanceException;
import com.pradyumn.payflow.exception.WalletNotFoundException;
import com.pradyumn.payflow.repository.TransactionRepository;
import com.pradyumn.payflow.repository.WalletRepository;

@Service
public class TransactionService {

        private final TransactionRepository transactionRepository;
        private final WalletRepository walletRepository;

        public TransactionService(
                        TransactionRepository transactionRepository,
                        WalletRepository walletRepository) {

                this.transactionRepository = transactionRepository;
                this.walletRepository = walletRepository;
        }

        @Transactional
        public TransactionResponse transferMoney(
                        CreateTransferRequest request) {

                Wallet senderWallet = walletRepository.findById(
                                request.getSenderWalletId())
                                .orElseThrow(() -> new WalletNotFoundException(
                                                "Sender wallet not found"));

                Wallet receiverWallet = walletRepository.findById(
                                request.getReceiverWalletId())
                                .orElseThrow(() -> new WalletNotFoundException(
                                                "Receiver wallet not found"));

                if (senderWallet.getBalance()
                                .compareTo(request.getAmount()) < 0) {

                        throw new InsufficientBalanceException(
                                        "Insufficient balance");
                }

                if (request.getSenderWalletId()
                                .equals(request.getReceiverWalletId())) {

                        throw new IllegalArgumentException(
                                        "Sender and receiver wallets cannot be the same");
                }

                senderWallet.setBalance(
                                senderWallet.getBalance()
                                                .subtract(request.getAmount()));

                receiverWallet.setBalance(
                                receiverWallet.getBalance()
                                                .add(request.getAmount()));

                walletRepository.save(senderWallet);
                walletRepository.save(receiverWallet);

                Transaction transaction = new Transaction();

                transaction.setSenderWallet(senderWallet);
                transaction.setReceiverWallet(receiverWallet);
                transaction.setAmount(request.getAmount());
                transaction.setStatus("SUCCESS");
                transaction.setCreatedAt(LocalDateTime.now());
                transaction.setTransactionReference(
                                UUID.randomUUID().toString());

                Transaction savedTransaction = transactionRepository.save(transaction);

                return mapToResponse(savedTransaction);
        }

        @Transactional
        public Wallet depositMoney(
                        Long walletId,
                        BigDecimal amount) {

                Wallet wallet = walletRepository.findById(walletId)
                                .orElseThrow(() -> new WalletNotFoundException(
                                                "Wallet not found"));

                wallet.setBalance(
                                wallet.getBalance().add(amount));

                return walletRepository.save(wallet);
        }

        private TransactionResponse mapToResponse(
                        Transaction transaction) {

                TransactionResponse response = new TransactionResponse();

                response.setTransactionId(
                                transaction.getId());

                response.setSenderWalletId(
                                transaction.getSenderWallet().getId());

                response.setReceiverWalletId(
                                transaction.getReceiverWallet().getId());

                response.setAmount(
                                transaction.getAmount());

                response.setStatus(
                                transaction.getStatus());

                response.setTransactionReference(
                                transaction.getTransactionReference());

                response.setCreatedAt(
                                transaction.getCreatedAt());

                return response;
        }

        public List<TransactionResponse> getTransactionHistory(
                        Long walletId) {

                List<Transaction> transactions = transactionRepository
                                .findBySenderWalletIdOrReceiverWalletId(
                                                walletId,
                                                walletId);

                return transactions.stream()
                                .map(this::mapToResponse)
                                .collect(Collectors.toList());
        }
}