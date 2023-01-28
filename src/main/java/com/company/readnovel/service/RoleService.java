package com.company.readnovel.service;

import com.company.readnovel.exceptions.NoSuchEntityException;
import com.company.readnovel.model.GenericResponse;
import com.company.readnovel.model.Role;
import com.company.readnovel.repository.RoleRepository;
import com.company.readnovel.utils.ResponseUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    private ResponseUtils responseUtils;
    private Logger logger;

    public RoleService(RoleRepository roleRepository, ResponseUtils responseUtils, Logger logger) {
        this.roleRepository = roleRepository;
        this.responseUtils = responseUtils;
        this.logger = logger;
    }

    public Role getRoleByName(String name) {
        var role = roleRepository
                .findByName(name)
                .orElseThrow(() -> new NoSuchEntityException("Could not find role with name: " + name));

        logger.info("Successfully retrieved role");
        return role;
    }

    public GenericResponse insertRole(Role role) {
        roleRepository.save(role);

        var response = responseUtils.getGenericSuccessResponse("Successfully inserted role");
        return response;
    }
}
