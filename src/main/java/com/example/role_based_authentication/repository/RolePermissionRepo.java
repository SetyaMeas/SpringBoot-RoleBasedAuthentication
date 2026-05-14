package com.example.role_based_authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.role_based_authentication.model.RolePermissionModel;

public interface RolePermissionRepo extends JpaRepository<RolePermissionModel, Integer> {

}
