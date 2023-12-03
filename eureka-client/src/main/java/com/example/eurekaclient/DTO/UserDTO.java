package com.example.eurekaclient.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private String id;
    private String email;
    private String password;
    private String role;
}
