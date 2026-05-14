package com.example.role_based_authentication.dto;

import lombok.Data;

@Data
public class CreateUserReq {
    private String name;
    private String email;
    private String password;
}
