package com.company.readnovel.model;

import jakarta.persistence.*;

@Table
@Entity
public class Novel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public String description;

    public Novel() {

    }
}
