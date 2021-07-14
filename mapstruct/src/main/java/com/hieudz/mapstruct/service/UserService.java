package com.hieudz.mapstruct.service;

import com.hieudz.mapstruct.dto.UserRequest;
import com.hieudz.mapstruct.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUser();

    void register(UserRequest userRequest);

    void deleteAll();
}
