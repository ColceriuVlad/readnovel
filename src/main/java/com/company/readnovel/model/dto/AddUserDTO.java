package com.company.readnovel.model.dto;

public class AddUserDTO {
    public String username;
    public String roleName;

    public AddUserDTO() {

    }

    public AddUserDTO(String username, String roleName) {
        this.username = username;
        this.roleName = roleName;
    }
}
