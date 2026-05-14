package com.gigajava.GigaJira.entity;

import com.gigajava.GigaJira.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Project extends BaseEntity {

    private String name;

    private Long createdBy;

    private Long companyId;
}
