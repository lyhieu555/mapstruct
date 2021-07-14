package com.hieudz.mapstruct.service.Impl;

import com.hieudz.mapstruct.dto.UserRequest;
import com.hieudz.mapstruct.dto.UserResponse;
import com.hieudz.mapstruct.entities.User;
import com.hieudz.mapstruct.exception.SpringException;
import com.hieudz.mapstruct.mapper.UserMapper;
import com.hieudz.mapstruct.repository.UserRepository;
import com.hieudz.mapstruct.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    public static final String EMAIL = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public List<UserResponse> getAllUser() {
        List<UserResponse> userResponses = userRepository.findAll()
                .stream()
                .map(userMapper::entityToDto)
                .collect(Collectors.toList());
        if(userResponses.size() > 0){
            logger.info("getAllUser : " + userResponses.size());
            return userResponses;
        } else{
            logger.info("getAllUser::null");
            return null;
        }
    }

    @Override
    public void register(UserRequest userRequest) throws SpringException {

        Optional<User> userEmail = userRepository.findByEmail(userRequest.getEmail());

        if(userEmail.isPresent()){
            logger.error(userRequest.getEmail() + " Đã tồn tại");
            throw new SpringException(userRequest.getEmail() + " Đã tồn tại");
        }

        User user = new User();

        user.setFullName(userRequest.getFullName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setPhone(null);
        user.setAddress(null);
        user.setImage(null);
        user.setCreateAt(Instant.now());
        user.setRole("USER");
        user.setActive(true);

        if(!userRequest.getEmail().matches(EMAIL)){
            logger.error("Email không hợp lệ !");
            throw new SpringException("Email không hợp lệ !");
        }else if(userRequest.getPassword().length() < 6){
            logger.error("Mật khẩu phải trên 6 ký tự !");
            throw new SpringException("Mật khẩu phải trên 6 ký tự !");
        }else {
            userRepository.save(user);
        }
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }
}