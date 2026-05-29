package com.pradyumn.payflow.repository;

import com.pradyumn.payflow.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
