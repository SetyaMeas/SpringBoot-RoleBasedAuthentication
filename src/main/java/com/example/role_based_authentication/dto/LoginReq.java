package com.example.role_based_authentication.dto;

import lombok.Data;

@Data
public class LoginReq {
    private String email;
    private String password;
}
