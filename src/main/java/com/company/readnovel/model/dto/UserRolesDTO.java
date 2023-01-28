package com.company.readnovel.model.dto;

import com.company.readnovel.model.User;

import java.util.HashSet;
import java.util.Set;

public class UserRolesDTO {
    public Integer id;
    public String username;
    public String password;
    public String email;
    public Set<RoleDTO> roles = new HashSet<>();

    public UserRolesDTO(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.email = user.email;

        for (var userRole : user.roles) {
            var role = new RoleDTO(userRole);
            roles.add(role);
        }
    }
}
