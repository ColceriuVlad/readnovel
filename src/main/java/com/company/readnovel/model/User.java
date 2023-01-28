package com.company.readnovel.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Table
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(unique = true)
    public String username;
    public String password;
    @Column(unique = true)
    public String email;
    @OneToMany
    public Set<Role> roles;

    @OneToMany
    public List<Novel> novelList;

    public User() {

    }
}
