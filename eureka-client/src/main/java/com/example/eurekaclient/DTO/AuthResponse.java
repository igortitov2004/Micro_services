package com.example.eurekaclient.DTO;

import lombok.Data;

@Data
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
