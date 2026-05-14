package com.gigajava.GigaJira.entity;


import com.gigajava.GigaJira.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Task extends BaseEntity {

    private String title;
    private String description;

    private Long projectId;
    private Long assignedTo;

    private Long statusId;
    private Long priorityId;

    private LocalDateTime dueDate;
}