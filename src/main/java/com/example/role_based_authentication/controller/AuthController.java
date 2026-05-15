package com.example.role_based_authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
