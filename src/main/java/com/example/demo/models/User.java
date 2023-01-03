package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", columnDefinition = "INT(8)")
    private Integer id;
    
    @Column(name = "`name`", columnDefinition = "VARCHAR(120)", unique = true, nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
