package com.example.controller.auth;

import com.example.common.BaseResponse;
import com.example.payload.request.auth.LoginDTORequest;
import com.example.payload.request.user.UserDTOCreate;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:8080/")
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UserService userService;


    @PostMapping("/register")
    public BaseResponse createUser(@RequestParam(value = "username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam(value = "phone", defaultValue = "") String phone,
                                   @RequestParam("fullName") String fullName,
                                   @RequestParam("role") String role,
                                   @RequestParam(value = "address",defaultValue = "") String address
                                  ) throws IOException {

        MultipartFile image = null;
        UserDTOCreate userDTOCreate = UserDTOCreate.builder()
                .fullName(fullName)
                .address(address)
                .password(password)
                .phoneNumber(phone)
                .address(address)
                .username(username)
                .role(role)
                .build();

        return userService.createUser(userDTOCreate, image);
    }


    @PostMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestBody  LoginDTORequest loginDTORequest) {

        return ResponseEntity.ok(userService.login(loginDTORequest));
    }
}
