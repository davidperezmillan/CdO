package com.davidperezmillan.cdoinfoshowservice.infraestructure.adapters.input.rest.findserie.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {

    @JsonProperty("title")
    private String title;

    @JsonProperty("id")
    private int id;

    @JsonProperty("poster")
    private String poster;

    @JsonProperty("cover")
    private String cover;

    @JsonProperty("isSerie")
    private boolean isSerie;

    @JsonProperty("year")
    private int year;

    @JsonProperty("rating")
    private double rating;

}
