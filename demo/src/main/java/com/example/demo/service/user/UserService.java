package com.example.demo.service.user;

import com.example.demo.dto.user.UserJoinRequestDTO;

public interface UserService {
    void registerUser(UserJoinRequestDTO userJoinRequestDto);
    String login(String userId, String userPassword);

}
