package com.example.role_based_authentication.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.role_based_authentication.model.UserModel;
import com.example.role_based_authentication.repository.PermissionRepo;
import com.example.role_based_authentication.repository.UserRepo;

@Service
public class CustomUserDetailtsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PermissionRepo permissionRepo;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserModel user = userRepo.findByEmail(usernameOrEmail)
            .orElseThrow(() -> new UsernameNotFoundException("Email: " + usernameOrEmail + " not found"));

        Set<String> permissions = permissionRepo.getByEmail(usernameOrEmail);
        Set<GrantedAuthority> authorities = permissions.stream()
            .map(i -> new SimpleGrantedAuthority(i))
            .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authorities
        );
    }
}
