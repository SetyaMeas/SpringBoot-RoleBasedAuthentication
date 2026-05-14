package com.example.role_based_authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.role_based_authentication.shared.Result;
import com.example.role_based_authentication.shared.ResultMapper;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create() {
        return ResponseEntity.ok("hi");
    } 
}
