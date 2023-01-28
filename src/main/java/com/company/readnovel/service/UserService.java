package com.company.readnovel.service;

import com.company.readnovel.exceptions.NoSuchEntityException;
import com.company.readnovel.model.GenericResponse;
import com.company.readnovel.model.User;
import com.company.readnovel.model.dto.UserRegistrationDTO;
import com.company.readnovel.model.dto.UserRolesDTO;
import com.company.readnovel.repository.UserRepository;
import com.company.readnovel.utils.ResponseUtils;
import com.company.readnovel.validator.UserValidator;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserValidator userValidator;
    private Logger logger;
    private ResponseUtils responseUtils;
    private RoleService roleService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserValidator userValidator, Logger logger, ResponseUtils responseUtils, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;
        this.logger = logger;
        this.responseUtils = responseUtils;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = getUserByUsername(username);
        return user;
    }

    public UserRolesDTO getUserById(Integer userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NoSuchEntityException("Could not find user with id: " + userId));
        logger.info("Successfully retrieved user");

        var userRolesDTO = new UserRolesDTO(user);
        logger.info("Successfully retrieved user roles DTO");

        return userRolesDTO;
    }

    public User getUserByUsername(String username) {
        var user = userRepository
                .findByUsername(username)
                .get();
        logger.info("Successfully retrieved user");

        return user;
    }

    public GenericResponse insertUser(UserRegistrationDTO userRegistrationDTO) {
        var user = new User(userRegistrationDTO);
        userValidator.validateUser(user);

        user.password = passwordEncoder.encode(userRegistrationDTO.password);
        logger.info("Successfully encoded password");

        var defaultRoleName = "default";
        var defaultRole = roleService.getRoleByName(defaultRoleName);

        user.addRole(defaultRole);
        logger.info("Successfully added role to user");

        userRepository.save(user);

        var response = responseUtils.getGenericSuccessResponse("Successfully inserted user");
        return response;
    }
}
