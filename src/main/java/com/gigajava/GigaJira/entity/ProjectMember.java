package com.gigajava.GigaJira.entity;

import com.gigajava.GigaJira.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class ProjectMember extends BaseEntity {

    private Long projectId;
    private Long userId;
}