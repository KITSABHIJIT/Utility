package com.test.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.auth.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
