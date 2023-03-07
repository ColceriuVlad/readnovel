package com.company.readnovel.model;

import com.company.readnovel.model.dto.AddUserDTO;
import com.company.readnovel.model.dto.RoleRegistrationDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Table
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(unique = true)
    @Size(min = 5, message = "Column name must have at least 5 characters")
    public String name;
    @ManyToMany(mappedBy = "roles")
    public Set<User> users = new HashSet<>();

    public Role() {

    }

    public Role(RoleRegistrationDTO roleRegistrationDTO) {
        this.name = roleRegistrationDTO.name;
    }
}
