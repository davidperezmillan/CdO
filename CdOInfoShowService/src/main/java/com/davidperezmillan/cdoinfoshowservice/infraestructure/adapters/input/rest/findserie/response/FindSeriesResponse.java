package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FindSeriesResponse {

    @JsonProperty("items")
    private Item[] items;

}
