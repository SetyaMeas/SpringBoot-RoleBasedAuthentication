package com.example.role_based_authentication.service;

import java.util.List;

import com.example.role_based_authentication.dto.CreateUserReq;
import com.example.role_based_authentication.dto.UserDto;
import com.example.role_based_authentication.shared.Result;

public interface UserService {
    Result<Boolean> create(CreateUserReq req); 
    Result<List<UserDto>> viewAll();
    Result<Boolean> grantAdmin(int userId);
}
