package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class InfoSeriesResponse {

    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("sinopsis")
    private String sinopsis;

    @JsonProperty("poster")
    private String poster;

    @JsonProperty("rating")
    private double rating;

    @JsonProperty("year")
    private int year;

    @JsonProperty("status")
    private String status;
}
