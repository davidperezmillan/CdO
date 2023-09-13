package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search;

import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.ErrorPlayMax;
import com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.RequestsPlayMax;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SearchPlayMaxResponse {

    @JsonProperty("error")
    private ErrorPlayMax error;

    @JsonProperty("requests")
    private RequestsPlayMax requests;

    @JsonProperty("result")
    private ResultSearch result;

}