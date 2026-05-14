package com.example.role_based_authentication.service;
import com.example.role_based_authentication.dto.JwtDto;
import com.example.role_based_authentication.dto.LoginReq;
import com.example.role_based_authentication.shared.Result;

public interface AuthService {
    Result<JwtDto> login(LoginReq req);
}
