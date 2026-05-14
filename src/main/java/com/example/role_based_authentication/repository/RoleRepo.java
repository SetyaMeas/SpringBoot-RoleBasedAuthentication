package com.example.role_based_authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.role_based_authentication.model.RoleModel;

public interface RoleRepo extends JpaRepository<RoleModel, Integer> {

}
