package com.hieudz.mapstruct.controller;


import com.hieudz.mapstruct.dto.UserRequest;
import com.hieudz.mapstruct.dto.UserResponse;
import com.hieudz.mapstruct.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping()
    public void register(@RequestBody UserRequest userRequest) {
        userService.register(userRequest);
    }

    @DeleteMapping
    public void deleteAll() {
        userService.deleteAll();
    }
}
