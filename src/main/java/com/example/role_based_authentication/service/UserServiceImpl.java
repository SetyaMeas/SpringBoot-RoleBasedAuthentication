package com.example.role_based_authentication.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.role_based_authentication.dto.CreateUserReq;
import com.example.role_based_authentication.repository.UserRepo;
import com.example.role_based_authentication.shared.Result;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public Result<Boolean> create(CreateUserReq req) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

}
