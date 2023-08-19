package com.example.service.user;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.common.BaseResponse;
import com.example.entity.User;
import com.example.exception.NotFoundException;
import com.example.jwt.JwtTokenProvider;
import com.example.payload.request.auth.LoginDTORequest;
import com.example.payload.request.user.UserDTOCreate;
import com.example.payload.response.auth.LoginDTOResponse;
import com.example.payload.response.user.UserDTOResponse;
import com.example.repository.UserRepository;
import com.example.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordencoder;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<UserDTOResponse> getAll() {
        List<UserDTOResponse> userDTOResponses = userRepository.findAll().stream().map(u -> mapperToUserDTO(u)).collect(Collectors.toList());
        return userDTOResponses;
    }


    @Override
    public BaseResponse createUser(UserDTOCreate userDTOCreate, MultipartFile image) throws IOException {
        BaseResponse response = new BaseResponse();
        String imageUser = null;

        if(image != null) {
            Map r = cloudinary.uploader().upload(image.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto",
                            "folder", "vue-spring"));
             imageUser = (String) r.get("secure_url");
        }



        BaseResponse validateResult = validateUser(userDTOCreate.getUsername());
        if (validateResult.isSuccess()) {
            User user = new User();
            BeanUtils.copyProperties(userDTOCreate, user);
            user.setImage(imageUser);
            user.setPassword(passwordencoder.encode(userDTOCreate.getPassword()));
            User userSave = userRepository.save(user);
            response.setSuccess(true);
            response.setData(mapperToUserDTO(userSave));
            response.setCode("User Created.");
        } else {
            response.setCode(validateResult.getCode());
            response.setSuccess(validateResult.isSuccess());
            response.setData(validateResult.getData());
        }

        return response;
    }


    @Override
    public Optional<UserDTOResponse> findUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTOResponse> findUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public BaseResponse updateUser(UserDTOCreate userDTOCreate) {
        return null;
    }

    @Override
    public String deleteUser(Long id) {
        return null;
    }

    @Override
    public BaseResponse login(LoginDTORequest loginDTORequest) {
        BaseResponse response = new BaseResponse();

        User user = userRepository.findByUsername(loginDTORequest.getUsername())
                .orElseThrow(() -> new NotFoundException("Username or password is incorrect "));
        boolean isAuthentication = passwordencoder.matches(loginDTORequest.getPassword(), user.getPassword());
        if (!isAuthentication) {
            throw new NotFoundException("Username or password is incorrect ");
        }
        String accessToken = jwtTokenProvider.generateToken(CustomUserDetails.mapUserToUserDetail(user));
        LoginDTOResponse loginDTOResponse = tokenPayLoad(user);
        loginDTOResponse.setToken(accessToken);
        response.setCode(String.valueOf(HttpStatus.OK));
        response.setSuccess(true);
        response.setData(loginDTOResponse);
        return response;

    }

    private LoginDTOResponse tokenPayLoad(User user) {
        return LoginDTOResponse.builder()
                .userDTOResponse(mapperToUserDTO(user))
                .type("Bearer")
                .build();
    }


    private UserDTOResponse mapperToUserDTO(User user) {
        return UserDTOResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .image(user.getImage())
                .role(String.valueOf(user.getRole()))
                .build();
    }


    private BaseResponse validateUser(String username) {
        BaseResponse response = new BaseResponse();
        response.setSuccess(true);
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            response.setSuccess(false);
            response.setData("Username is not exits");
            response.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
            return response;
        }
        return response;
    }
}
