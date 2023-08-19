package com.example.payload.response.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTOResponse {

    private Long id;

    private String username;

    private String address;

    private String phoneNumber;

    private String image;

    private String role;
}
