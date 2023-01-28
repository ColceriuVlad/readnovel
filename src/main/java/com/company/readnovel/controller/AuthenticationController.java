package com.company.readnovel.controller;

import com.company.readnovel.model.GenericResponse;
import com.company.readnovel.model.dto.AuthenticationDTO;
import com.company.readnovel.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/authentication")
@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<GenericResponse> login(@RequestBody AuthenticationDTO authenticationDTO) {
        var response = authenticationService.login(authenticationDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
