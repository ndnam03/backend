package com.example.payload.request.auth;

import lombok.Data;

@Data
public class LoginDTORequest {

    private String username;

    private String password;
}
