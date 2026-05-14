package com.gigajava.GigaJira.entity;

import com.gigajava.GigaJira.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Comments extends BaseEntity {
    private Long taskId;
    private Long authorId;

    @Column(nullable = false)
    private String content;
}
