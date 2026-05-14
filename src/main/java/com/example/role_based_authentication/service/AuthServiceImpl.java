package com.example.role_based_authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.role_based_authentication.dto.JwtDto;
import com.example.role_based_authentication.dto.LoginReq;
import com.example.role_based_authentication.security.util.JwtTokenProvider;
import com.example.role_based_authentication.shared.Result;

@Component
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Result<JwtDto> login(LoginReq req) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        return Result.Success(new JwtDto(token));
    }
}
