package com.gigajava.GigaJira.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatusUpdateRequest {

    @JsonProperty("status_id")
    private Long statusId;
}
