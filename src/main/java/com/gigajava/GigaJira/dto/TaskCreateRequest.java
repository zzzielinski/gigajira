package com.gigajava.GigaJira.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskCreateRequest {

    private String title;

    private String description;

    @JsonProperty("project_id")
    private Long projectId;

    @JsonProperty("assigned_to")
    private Long assignedTo;

    @JsonProperty("status_id")
    private Long statusId;

    @JsonProperty("priority_id")
    private Long priorityId;

    @JsonProperty("due_date")
    private LocalDateTime dueDate;
}
