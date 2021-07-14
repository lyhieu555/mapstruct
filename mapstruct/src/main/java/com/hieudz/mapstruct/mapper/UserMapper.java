package com.hieudz.mapstruct.mapper;

import com.hieudz.mapstruct.dto.UserRequest;
import com.hieudz.mapstruct.dto.UserResponse;
import com.hieudz.mapstruct.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User dtoToEntity(UserRequest userRequest);

    UserResponse entityToDto(User user);
}
