package com.company.readnovel.controller;

import com.company.readnovel.model.dto.AddUserDTO;
import com.company.readnovel.model.dto.UserRegistrationDTO;
import com.company.readnovel.model.dto.UserRolesDTO;
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
    public ResponseEntity<UserRolesDTO> getUserById(@PathVariable Integer id) {
        var userRolesDTO = userService.getUserById(id);
        var response = new ResponseEntity<>(userRolesDTO, HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity insertUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        var response = userService.insertUser(userRegistrationDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity addRoleToUser(@RequestBody AddUserDTO addUserDTO) {
        var response = userService.addRoleToUser(addUserDTO);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
