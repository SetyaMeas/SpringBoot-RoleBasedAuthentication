package com.example.role_based_authentication.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.role_based_authentication.model.PermissionModel;

public interface PermissionRepo extends JpaRepository<PermissionModel, Integer> {
    @Query("""
        SELECT CONCAT(p.resource, ':', p.action)
        FROM PermissionModel p
        INNER JOIN p.rolePermissions rp
        INNER JOIN rp.role r
        INNER JOIN r.userRoles ur
        INNER JOIN ur.user u
        WHERE u.email = :email
            """)
    Set<String> getByEmail(@Param("email") String email);
}
