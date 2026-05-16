package com.gigajava.GigaJira.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Long projectId;

    private Long assignedTo;

    private Long statusId;

    private Long priorityId;

    private Long createdBy;

    private LocalDateTime dueDate;
}