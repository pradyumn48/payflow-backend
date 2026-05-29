package com.pradyumn.payflow.repository;

import com.pradyumn.payflow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}