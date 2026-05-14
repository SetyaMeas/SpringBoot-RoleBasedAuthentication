package com.example.role_based_authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.role_based_authentication.model.UserRoleModel;

public interface UserRoleRepo extends JpaRepository<UserRoleModel, Integer> {

}
