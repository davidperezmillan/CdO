package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KeepAliveRequest {

    @JsonProperty("name")
    private String name;
}
