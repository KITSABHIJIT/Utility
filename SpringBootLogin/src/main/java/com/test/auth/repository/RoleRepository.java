package com.test.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
