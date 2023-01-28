package com.company.readnovel.model.dto;

import com.company.readnovel.model.Role;

public class RoleDTO {
    public Integer id;
    public String name;

    public RoleDTO(Role role) {
        this.id = role.id;
        this.name = role.name;
    }
}
