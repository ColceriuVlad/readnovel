package com.company.readnovel.service;

import com.company.readnovel.model.GenericResponse;
import com.company.readnovel.model.Role;
import com.company.readnovel.repository.RoleRepository;
import com.company.readnovel.utils.ResponseUtils;
import com.company.readnovel.validator.RoleValidator;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    private ResponseUtils responseUtils;
    private RoleValidator roleValidator;

    public RoleService(RoleRepository roleRepository, ResponseUtils responseUtils, RoleValidator roleValidator) {
        this.roleRepository = roleRepository;
        this.responseUtils = responseUtils;
        this.roleValidator = roleValidator;
    }

    public GenericResponse insertRole(Role role) {
        roleValidator.validateRole(role);
        roleRepository.save(role);

        var response = responseUtils.getGenericSuccessResponse("Successfully inserted role");
        return response;
    }
}
