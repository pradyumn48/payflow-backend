package com.pradyumn.payflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradyumn.payflow.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    
}
