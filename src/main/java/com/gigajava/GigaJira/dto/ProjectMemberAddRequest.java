package com.gigajava.GigaJira.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMemberAddRequest {

    @JsonProperty("user_id")
    private Long userId;
}
