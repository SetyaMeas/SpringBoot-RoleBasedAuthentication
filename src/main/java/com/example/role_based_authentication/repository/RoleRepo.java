package com.example.role_based_authentication.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.role_based_authentication.model.RoleModel;

public interface RoleRepo extends JpaRepository<RoleModel, Integer> {
    @Query("""
            SELECT r
            FROM RoleModel r
            WHERE r.name = :name
            """)
    Optional<RoleModel> getByName(@Param("name") String name);

    @Query("""
            SELECT r.name
            FROM RoleModel r
            INNER JOIN r.userRoles ur
            INNER JOIN ur.user u
            WHERE u.id = :id
            """)
    Set<String> getUserRolesByUserId(@Param("id") int id);
}
