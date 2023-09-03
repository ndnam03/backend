package com.example.payload.request.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTOCreate {

    private Long id;
    private String fullName;

    private String username;

    private String password;

    private String address;

    private String phoneNumber;

    private String image;

    private String role;
}
