package com.hieudz.mapstruct.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserResponse{

    private Long id;

    private String email;

    private String fullName;

    private String phone;

    private String address;

    private String image;

    private Instant createAt;

    private String role;

    private Boolean active;
}
