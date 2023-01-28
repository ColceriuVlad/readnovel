package com.company.readnovel.controller;

import com.company.readnovel.model.User;
import com.company.readnovel.model.dto.UserRegistrationDTO;
import com.company.readnovel.service.UserService;
import com.company.readnovel.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private UserService userService;
    private ResponseUtils responseUtils;

    public UserController(UserService userService, ResponseUtils responseUtils) {
        this.userService = userService;
        this.responseUtils = responseUtils;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        var user = userService.getUserById(id);
        var response = new ResponseEntity<>(user, HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity insertUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        var response = userService.insertUser(userRegistrationDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
