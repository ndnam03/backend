package com.example.service.user;

import com.example.common.BaseResponse;
import com.example.payload.request.auth.LoginDTORequest;
import com.example.payload.request.user.UserDTOCreate;
import com.example.payload.response.user.UserDTOResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTOResponse> getAll();

    BaseResponse createUser(UserDTOCreate userDTOCreate, MultipartFile image) throws IOException;

    Optional<UserDTOResponse> findUserById(Long id);

    Optional<UserDTOResponse> findUserByUsername(String username);

    BaseResponse updateUser(UserDTOCreate userDTOCreate);

    String deleteUser(Long id);

    BaseResponse login(LoginDTORequest loginDTORequest);
}
