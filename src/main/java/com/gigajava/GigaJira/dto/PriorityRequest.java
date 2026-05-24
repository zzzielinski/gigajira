package com.gigajava.GigaJira.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PriorityRequest {

    private Integer level;
    private String label;
}
