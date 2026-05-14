package com.gigajava.GigaJira.entity;

import com.gigajava.GigaJira.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Priority extends BaseEntity {

    private int level;
    private String label;
}