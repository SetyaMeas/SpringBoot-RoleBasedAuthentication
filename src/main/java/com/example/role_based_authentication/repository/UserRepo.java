package com.example.role_based_authentication.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.role_based_authentication.dto.UserDto;
import com.example.role_based_authentication.model.UserModel;

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

	@Query(value = """
		SELECT 
                        u.id as userId,
			u.name, 
			u.email,
			ARRAY_AGG(r.name) as roles,
			u.created_at as createdAt
		FROM tbl_user u
		LEFT JOIN tbl_user_role ur ON u.id = ur.user_id
		LEFT JOIN tbl_role r ON ur.role_id = r.id
		GROUP BY u.id, u.name, u.email, u.created_at
			""", nativeQuery = true)
	List<UserDto> viewAll();
}
