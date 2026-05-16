package com.gigajava.GigaJira.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "project_members",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"projectId", "userId"})
        }
)
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    private Long userId;
}