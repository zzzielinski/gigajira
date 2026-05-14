package com.gigajava.GigaJira.entity;

import com.gigajava.GigaJira.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Domain extends BaseEntity {

    private String name;

    private Long userId;
}