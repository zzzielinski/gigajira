package com.gigajava.GigaJira.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DomainCreateRequest {

    private String name;

    @JsonProperty("company_id")
    private Long companyId;
}
