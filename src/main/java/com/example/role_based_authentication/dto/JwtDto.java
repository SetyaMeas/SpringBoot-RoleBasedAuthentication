package com.example.role_based_authentication.dto;

import lombok.Getter;
import lombok.Setter;

public class JwtDto {
    @Getter @Setter
    private String accessToken;

    @Getter
    private String type = "Bearer";

    public JwtDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
