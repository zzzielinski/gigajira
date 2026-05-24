package com.gigajava.GigaJira.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {

    private String username;
    private String email;
    private String password;

    @JsonProperty("role_id")
    private Long roleId;

    @JsonProperty("domain_id")
    private Long domainId;
}
