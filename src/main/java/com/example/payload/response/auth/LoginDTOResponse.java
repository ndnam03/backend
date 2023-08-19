package com.example.payload.response.auth;

import com.example.payload.response.user.UserDTOResponse;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTOResponse {

    private String type ;
    private String token;

    UserDTOResponse userDTOResponse;
}
