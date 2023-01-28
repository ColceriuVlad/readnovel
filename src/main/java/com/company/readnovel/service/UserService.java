package com.company.readnovel.service;

import com.company.readnovel.exceptions.NoSuchEntityException;
import com.company.readnovel.mapper.UserRegistrationDTOToUserMapper;
import com.company.readnovel.model.GenericResponse;
import com.company.readnovel.model.User;
import com.company.readnovel.model.dto.UserRegistrationDTO;
import com.company.readnovel.repository.UserRepository;
import com.company.readnovel.utils.ResponseUtils;
import com.company.readnovel.validator.UserValidator;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
public class UserService {
    private UserRepository userRepository;
    private UserRegistrationDTOToUserMapper userRegistrationDTOToUserMapper;
    private PasswordEncoder passwordEncoder;
    private UserValidator userValidator;
    private Logger logger;
    private ResponseUtils responseUtils;

    public UserService(UserRepository userRepository, UserRegistrationDTOToUserMapper userRegistrationDTOToUserMapper, PasswordEncoder passwordEncoder, UserValidator userValidator, Logger logger, ResponseUtils responseUtils) {
        this.userRepository = userRepository;
        this.userRegistrationDTOToUserMapper = userRegistrationDTOToUserMapper;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;
        this.logger = logger;
        this.responseUtils = responseUtils;
    }

    public User getUserById(Integer userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NoSuchEntityException("Could not find user with id: " + userId));
        return user;
    }

    public User getUserByUsername(String username) {
        var user = userRepository
                .findByUsername(username)
                .get();
        return user;
    }

    public GenericResponse insertUser(UserRegistrationDTO userRegistrationDTO) {
        var user = userRegistrationDTOToUserMapper.getUser(userRegistrationDTO);
        userValidator.validateUser(user);

        user.password = passwordEncoder.encode(userRegistrationDTO.password);
        logger.info("Successfully encoded password");

        userRepository.save(user);

        var response = responseUtils.getGenericSuccessResponse("Successfully inserted user");
        return response;
    }
}
