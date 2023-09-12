package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FindSeriesRequest {

    @JsonProperty("title")
    private String title;
}
