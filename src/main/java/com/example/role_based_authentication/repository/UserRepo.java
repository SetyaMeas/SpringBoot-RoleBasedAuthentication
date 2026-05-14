package com.example.role_based_authentication.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.role_based_authentication.dto.UserDetailDTO;
import com.example.role_based_authentication.model.UserModel;
import com.example.role_based_authentication.model.UserRoleModel;

public interface UserRepo extends JpaRepository<UserModel, Integer> {

    @Query("""
            SELECT r.name
            FROM UserModel u
            INNER JOIN u.userRoles ur
            INNER JOIN ur.role r
            WHERE u.id = :id
            """)
    Set<String> getRolesByUserId(@Param("id") int id);

    @Query("""
            SELECT r.name
            FROM UserModel u
            INNER JOIN u.userRoles ur
            INNER JOIN ur.role r
            WHERE u.email = :email
            """)
    Set<String> getRolesByEmail(@Param("email") String email);

    @Query("""
			SELECT u
			FROM UserModel u
			WHERE u.email = :email
                    """)
    Optional<UserModel> findByEmail(@Param("email") String email);


}
