package com.gigajava.GigaJira.entity;

import com.gigajava.GigaJira.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Role extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}