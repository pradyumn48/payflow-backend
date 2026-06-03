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


@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletService(
        WalletRepository walletRepository,
        UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(CreateWalletRequest request){

        if(walletRepository.existsByUserId(request.getUserId())){
        throw new WalletAlreadyExistsException(
                "Wallet already exists for this user");
        }
        
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        
        Wallet wallet = new Wallet();

        wallet.setUser(user);
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setCreatedAt(LocalDateTime.now()); 
        
        return walletRepository.save(wallet);
        
    }
    
    public WalletResponse getWalletById(Long walletId) {

    Wallet wallet = walletRepository.findById(walletId)
            .orElseThrow(() -> new WalletNotFoundException("Wallet not found"));

    WalletResponse response = new WalletResponse();

    response.setId(wallet.getId());
    response.setBalance(wallet.getBalance());
    response.setUserId(wallet.getUser().getId());
    response.setUserName(wallet.getUser().getName());
    response.setCreatedAt(wallet.getCreatedAt());

    return response;
}
    
}
