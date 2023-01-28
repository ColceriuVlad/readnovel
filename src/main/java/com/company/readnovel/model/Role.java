package com.company.readnovel.model;

import jakarta.persistence.*;

@Table
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    @Column(unique = true)
    public String name;
}
