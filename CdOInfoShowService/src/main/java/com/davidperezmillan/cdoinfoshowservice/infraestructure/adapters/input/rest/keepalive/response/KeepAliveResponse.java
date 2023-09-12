package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.keepalive.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class KeepAliveResponse {

    @JsonProperty("comment")
    private String comment;

}
