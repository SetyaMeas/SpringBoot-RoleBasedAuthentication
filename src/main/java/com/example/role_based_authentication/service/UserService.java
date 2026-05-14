package com.example.role_based_authentication.service;

import com.example.role_based_authentication.dto.CreateUserReq;
import com.example.role_based_authentication.shared.Result;

public interface UserService {
    Result<Boolean> create(CreateUserReq req); 
}
