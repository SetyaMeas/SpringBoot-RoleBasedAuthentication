package com.example.role_based_authentication.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.role_based_authentication.dto.JwtDto;
import com.example.role_based_authentication.dto.LoginReq;
import com.example.role_based_authentication.service.AuthService;
import com.example.role_based_authentication.shared.Result;
import com.example.role_based_authentication.shared.ResultMapper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req) {
        Result<JwtDto> result = authService.login(req);
        return ResultMapper.toResponse(result);
    }
}
