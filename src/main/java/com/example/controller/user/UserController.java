package com.example.controller.user;


import com.example.common.BaseResponse;

import com.example.exception.NotFoundException;
import com.example.payload.response.user.UserDTOResponse;
import com.example.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/getAll")
    public List<UserDTOResponse> getAllUser() {
        return  userService.getAll();
    }

    @GetMapping("/id/{id}")
    public UserDTOResponse getUserById(@PathVariable("id")Long id) {
        return userService.findUserById(id);
    }
}
