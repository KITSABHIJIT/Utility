package com.test.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.account.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
