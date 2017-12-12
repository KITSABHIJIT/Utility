package com.test.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.account.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
