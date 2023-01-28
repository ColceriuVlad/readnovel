package com.company.readnovel.controller;

import com.company.readnovel.model.GenericResponse;
import com.company.readnovel.model.Role;
import com.company.readnovel.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/role")
@RestController
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        var role = roleService.getRoleByName(name);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenericResponse> insertRole(@RequestBody Role role) {
        var response = roleService.insertRole(role);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
