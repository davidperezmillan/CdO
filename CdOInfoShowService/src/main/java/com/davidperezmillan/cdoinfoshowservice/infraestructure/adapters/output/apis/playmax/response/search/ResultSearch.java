package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.output.apis.playmax.response.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResultSearch {

    @JsonProperty("ficha")
    private FichaSearch ficha;
}
