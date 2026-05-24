package com.gigajava.GigaJira.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyCreateRequest {

    private String name;

    @JsonProperty("domain_id")
    private Long domainId;
}
