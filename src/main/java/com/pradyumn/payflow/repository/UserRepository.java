package com.pradyumn.payflow.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradyumn.payflow.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}