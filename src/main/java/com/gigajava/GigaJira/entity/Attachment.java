package com.gigajava.GigaJira.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;

    private Long uploadedBy;

    private String fileUrl;

    private LocalDateTime createdAt = LocalDateTime.now();
}