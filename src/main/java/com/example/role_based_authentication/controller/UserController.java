package com.example.role_based_authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.role_based_authentication.dto.CreateUserReq;
import com.example.role_based_authentication.service.UserService;
import com.example.role_based_authentication.shared.ResultMapper;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('USER:WRITE')")
    public ResponseEntity<?> create(@RequestBody CreateUserReq req) {
        return ResultMapper.toResponse(userService.create(req));
    } 

    @GetMapping("/view-all")
    @PreAuthorize("hasAuthority('USER:READ')")
    public ResponseEntity<?> viewAll() {
        return ResultMapper.toResponse(userService.viewAll());
    }

    @PutMapping("/grand-admin")
    @PreAuthorize("hasAuthority('USER:WRITE')")
    public ResponseEntity<?> grandAdmin(@RequestParam int userId) {
        return ResultMapper.toResponse(userService.grantAdmin(userId));
    }
}
