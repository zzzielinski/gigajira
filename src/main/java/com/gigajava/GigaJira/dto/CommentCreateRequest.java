package com.gigajava.GigaJira.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {

    @JsonProperty("task_id")
    private Long taskId;

    private String content;
}
