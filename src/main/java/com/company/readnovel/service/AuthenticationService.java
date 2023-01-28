package com.company.readnovel.service;

import com.company.readnovel.model.GenericResponse;
import com.company.readnovel.model.dto.AuthenticationDTO;
import com.company.readnovel.utils.JwtUtils;
import com.company.readnovel.utils.ResponseUtils;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private ResponseUtils responseUtils;
    private Logger logger;

    public AuthenticationService(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils, ResponseUtils responseUtils, Logger logger) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.responseUtils = responseUtils;
        this.logger = logger;
    }

    public GenericResponse login(AuthenticationDTO authenticationDTO) {
        var username = authenticationDTO.username;
        var password = authenticationDTO.password;

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        logger.info("Successfully authenticated");

        var user = userService.getUserByUsername(password);
        var message = jwtUtils.signToken(user);
        logger.info("Successfully signed jwt token");
        var response = responseUtils.getGenericSuccessResponse(message);
        return response;
    }
}
